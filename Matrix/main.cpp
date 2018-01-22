#include <iostream>
#include <cstdlib>
using namespace std;

int n=0,m=0,b=0;

int main()
{
    cout << "Choose a number to run the Matrix.\n\n";
    n=0;m=0;
    while(n<100000 or n>999999998){
        cin>>n;
        cout<<"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        if(n<100000){cout<<"Number is too small, try again.\n\n\n";}
        if(n>999999998){cout<<"Number is too big try again.";}
        if(n>999999999){cout<<"Number is so big you just crashed the Matrix. Just saying.";}
    }
    cout<<"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nMake sure to look for patterns!!! ;D";
    while(m != 1000000000){m++;} //// pause the screen
    while(1){cout << n; cout << b; b++;}
    cout<<"Matrix program script finished.\n\n";
    system("pause");
}
