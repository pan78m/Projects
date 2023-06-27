#include<stdio.h>
int main()
{
    int arr[100],i,j,n,pos,value;
    printf("Enter number you want=");
    scanf("%d",&n);
    printf("\nEnter Array element\n");
    for(i=0;i<n;i++)
        scanf("%d",&arr[i]);


    printf("Enter The location to insert element=");
    scanf("%d",&pos);
    printf("Enter value to insert=");
    scanf("%d",&value);

    for(i=n-1;i>=pos-1;i--)

    arr[i+1]=arr[i];

    arr[pos-1]=value;

     for(i=0;i<n;i++)

    printf("%d\n",arr[i]);

    return 0;


}
