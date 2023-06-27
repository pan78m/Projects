#include <stdio.h>
void main() {
 int A[50][50];
 int i, j, M, N;
 int size;
 int rowsum, columnsum, diagonalsum;
 int magic = 0;
 while(1)
 {

 printf("\nEnter the order of the matrix:\n");
 scanf("%d %d",&M,&N);
 if(M==N) {
 printf("Enter the elements of matrix \n");
 for(i=0; i<M; i++)
    {
 for(j=0; j<N; j++)
    {
 scanf("%d", &A[i][j]);
    }
 }

 printf("\n\nMATRIX is\n");
 for(i=0; i<M; i++) {
 for(j=0; j<N; j++) {
 printf("%3d\t", A[i][j]);
 }
 printf("\n");
 }


 diagonalsum = 0;
 for(i=0; i<M; i++) {
 for(j=0; j<N; j++) {
 if(i==j) {
 diagonalsum = diagonalsum + A[i][j];
 }
 }
 }


 for(i=0; i<M; i++) {
 rowsum = 0;
 for(j=0; j<N; j++) {
 rowsum = rowsum + A[i][j];
 }
 if(rowsum != diagonalsum) {
 printf("\nThis is not a Lo Shu a magic square");
 return;
 }
 }

 for(i=0; i<M; i++) {
 columnsum = 0;
 for(j=0; j<N; j++) {
 columnsum = columnsum + A[j][i];
 }
 if(columnsum != diagonalsum) {
 printf("\nThis is not a Lo Shu a magic square\n");
 return;
 }
 }

 printf("\nThis is a Lo Shu Magic Square\nAnd, the magic number is 15.");
 } else {
 printf("\n\nPlease enter the square matrix order(m=n) \n\n");
 }
}
}
