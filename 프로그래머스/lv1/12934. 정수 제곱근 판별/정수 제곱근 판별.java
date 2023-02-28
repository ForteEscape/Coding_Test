class Solution {
    public long solution(long n) {
        long nn = n / 2;
        long count;

        for (count = 1; nn >= 0; count++){
            nn = nn - count;
        }

        long answer = (count - 1) * (count - 1) == n ? count * count : -1;

        return answer;
    }
}