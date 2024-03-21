#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <queue>
#include <string.h>
#include <map>
#include <set>

#define MAX_INT 10000000
using namespace std;

/*
1. findParent -> in이 0인 곳 찾기
2. dp inf로 init
3. leaf 노드부터 거슬러 올라가면서 dp 수행 -> 레벨 별로 돌려야 함
	- leaf 노드 찾는 방법 - size가 0인 곳 찾기
	- dp[i][0]: i번째 선택 안했을 때 필요한 얼리어답터 수 (자식 선택x, 자식 선택o 둘다 가능 -> min 값)
	- dp[i][1]: i번째 선택했을 때 필요한 얼리어답터 수 (+1 해줘야 함)
*/

int N;
vector<vector<int>> G;
vector<int> in;
vector<vector<int>> dp;
vector<int> leafNodes;
vector<vector<int>> level; //각 level별 node
vector<bool> visit;
int nowLevel;
int root;

void findRootAndLeafNodes() {
	for (int i = 1; i <= N; i++) {
		if (G[i].size() == 0) {
			leafNodes.push_back(i);
		}
		if (in[i] == 0) root = i;
	}
}

void printLevel() {
	for (int i = 0; i < level.size(); i++) {
		printf("level %d\n", i);
		for (int j = 0; j < level[i].size(); j++) {
			printf("%d ", level[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}

void initLevel() {
	queue<pair<int, int>> q; //n, level
	q.push({root, 0});

	while (!q.empty()) {
		int nowNode = q.front().first;
		int nowLevel = q.front().second;
		q.pop();

		if (nowLevel == level.size()) {
			level.push_back(vector<int>());
		}
		level[nowLevel].push_back(nowNode);

		for (int i = 0; i < G[nowNode].size(); i++) {
			q.push({ G[nowNode][i], nowLevel + 1 });
		}
	}
}

void initDP() {
	for (int n : leafNodes) {
		dp[n][0] = 0;
		dp[n][1] = 1;
	}
}

int dfs(int n) { //후위순회 하면서 dp 갱신
	visit[n] = true;
	//printf("now: %d\n", n);
	//if (G[n].size() == 1) {
	//	dp[n][0] = 0;
	//	dp[n][1] = 1;
	//	return;
	//}
	int newDp0 = 0, newDp1 = 1;
	for (int i = 0; i < G[n].size(); i++) {
		int nextNode = G[n][i];		
		//printf("nextNode: %d\n", nextNode);

		if (!visit[nextNode]) {
			dfs(nextNode);
			newDp0 += dp[nextNode][1];
			newDp1 += min(dp[nextNode][0], dp[nextNode][1]);
		}

		
	}
	dp[n][0] = min(dp[n][0], newDp0);
	dp[n][1] = min(dp[n][1], newDp1);
	//printf("new 0: %d 1: %d\n", newDp0, newDp1);
	//printf("0: %d  1: %d\n", dp[n][0], dp[n][1]);
	return min(dp[n][0], dp[n][1]);
}

void makeDP() {
	//레벨 별 갱신

	int nowLevel = level.size()-1;

	for (int l = nowLevel; l >= 0; l--) {
		//printf("nowLevel: %d\n", l);
		for (int j = 0; j < level[l].size(); j++) {
			int nowNode = level[l][j];
			//printf("nowNode: %d\n", nowNode);
			int newDp0 = 0, newDp1 = 1;
			for (int i = 0; i < G[nowNode].size(); i++) {
				int child = G[nowNode][i];
				newDp0 += dp[child][1];
				newDp1 += min(dp[child][0], dp[child][1]);
			}
			dp[nowNode][0] = min(dp[nowNode][0], newDp0);
			dp[nowNode][1] = min(dp[nowNode][1], newDp1);
			//printf("0: %d 1: %d\n", dp[nowNode][0], dp[nowNode][1]);
		}
	}	
}

int main() {
	
	int a, b;

	scanf("%d", &N);
	
	G = vector<vector<int>>(N + 1, vector<int>());
	//in = vector<int>(N + 1, 0);
	dp = vector<vector<int>>(N + 1, vector<int>(2, MAX_INT));
	visit = vector<bool>(N + 1, false);

	for (int i = 0; i < N-1; i++) {
		scanf("%d %d", &a, &b);
		G[a].push_back(b);
		G[b].push_back(a);
		//in[b]++;
	}

	//findRootAndLeafNodes();
	//initLevel();
	//printLevel();
	//initDP();
	//makeDP();

	int answer = dfs(1);

	//int answer = min(dp[1][0], dp[1][1]);
	printf("%d", answer);

	return 0;
}

/*
*/