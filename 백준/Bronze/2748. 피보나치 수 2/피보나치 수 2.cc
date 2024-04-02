#include<stdio.h>

long long fibo_table[100] = {0, };

void fibo(int x);

int main()
{
	int number;
	
	scanf("%d", &number);
	
	fibo(number);
}

void fibo(int x)
{
	fibo_table[1] = 1;
	
	if(x == 1){
		printf("%lld", fibo_table[1]);
		return;
	}
	else
	{
		for(int i = 2; i<=x; i++)
		{
			fibo_table[i] = fibo_table[i-2] + fibo_table[i-1];
		}
	}
	
	printf("%lld", fibo_table[x]);
}