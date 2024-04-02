#include<stdio.h>

long long pado_table[100] = {0, };

void pado(int number);

int main()
{
	int test_case;
	int test_num;
	
	scanf("%d", &test_case);
	
	for(int i = 0; i<test_case; i++)
	{
		scanf("%d", &test_num);
		
		pado(test_num);
	}
}

void pado(int number)
{
	pado_table[1] = 1;
	pado_table[2] = 1;
	
	for(int i = 3; i<=number; i++)
	{
		pado_table[i] = pado_table[i-3] + pado_table[i-2];
	}
	
	printf("%lld\n", pado_table[number]);
	
}