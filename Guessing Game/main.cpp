#include <iostream>
#include <cstdlib>
#include <ctime>
#include <stdio.h>
using namespace std;

int number,guess=0,maximum=0,choice=0,restart=1,tries=0,gameRun=1,times=0,compare=0,go=0;

void personGuess(){
    while(restart==1){
        tries=0;
        cout<<"\n\n\n\n\nWhat do you want the maximum number to be?\n";
        cin>>maximum;
        srand(time(0));
        number=((rand()%maximum)+1);
        cout<<"\n\nWhats your Guess?\n";
        guess=-1;
        while(guess!=number){
            cin>>guess;
            tries++;
            cout<<"\n\n\n\n\n";
            cout<<endl<<endl;
            if(guess>number){cout<<"Too high, try again.\n";}
            if(guess<number){cout<<"Too low, try again.\n";}
        }
        cout<<"You found the random number, "<<number<<"!!! Good job!!\n\nTries: "<<tries<<"\n\n\n\n\nWould you like to try again?    1} Yes   2} No\n\n";
        restart=0;
        while(restart!=1 and restart!=2){
            cin>>restart;
        }
        cout<<"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    }
}


void compGuess(){
    restart=1;
    while(restart==1){
        times=1,compare=0,go=0,tries=0;
        cout<<"\n\nNOTE: Make sure your number is less than 999,999,999 and more than -999,999,999.\n\n\n\n\n";
        cout<<"Choose the number that your computer will have to guess.\n\n";
        cin>>number;
        cout<<"\n\n\n";
        if(number<0){maximum=number*-1;}
        if(number>0){maximum=number*2;}
        if(maximum==1){maximum=maximum*3;}
        cout<<"\n\n\n";
        srand(time(0));
        guess=((rand()%maximum)+1);
        cout<<guess<<endl;
        tries++;
        while(guess!=number){
            if(guess>number){
                if(times==1){
                    guess=guess-maximum/2;
                }
                if(times==2){
                    guess=guess-maximum/3;
                }
                if(times==3){
                    guess=guess-maximum/4;
                }
                if(times==4){
                    guess=guess-maximum/4/4;
                }
                if(times==5){
                    guess=guess-100;
                }
                if(times==6){
                    guess=guess-50;
                }
                if(times==7){
                    guess=guess-25;
                }
                if(times==8){
                    guess=guess-10;
                }
                if(times==9){
                    guess=guess-5;
                }
                if(times==10){
                    guess--;
                }
                compare=1;
                tries++;
                cout<<guess<<endl;
            }
            if(compare==1 and guess<number and times!=10){
                times=times+1;
            }
            if(guess<number or go==2){
                if(times==1){
                    guess=guess+maximum/2;
                }
                if(times==2){
                    guess=guess+maximum/3;
                }
                if(times==3){
                    guess=guess+maximum/4;
                }
                if(times==4){
                    guess=guess+maximum/4/4;
                }
                if(times==5){
                    guess=guess+100;
                }
                if(times==6){
                    guess=guess+50;
                }
                if(times==7){
                    guess=guess+25;
                }
                if(times==8){
                    guess=guess+10;
                }
                if(times==9){
                    guess=guess+5;
                }
                if(times==10){
                    guess++;
                }
                compare=2;
                tries++;
                cout<<guess<<endl;
            }
            if(compare==2 and guess>number and times!=10){
                times=times+1;
            }
        }
        cout<<"\a\n\n\nYour computer found your number, "<<number<<"!!! It found it in "<<tries<<" tries.\n\n\n";
        cout<<"Would you like to restart? (1) for Yes and (2) for No. ";
        restart=0;
        while(restart!=1 and restart!=2){
            cin>>restart;
        }
        cout<<"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    }
}
int main()
{
    while(gameRun==1){
        choice=0;
        cout<<"Do you want to try and guess a computer generated number (1),\nor have the computer guess a number of your choice (2)?\n\n";
        while(choice!=1 and choice!=2){
            cin>>choice;
        }
        cout<<"\n\n\n\n\n";
        if(choice==1){
            personGuess();
        }
        if(choice==2){
            compGuess();
        }
        cout<<"Do you want to repick the game type (1),\nor completely quit the game (2)?\n\n";
        gameRun=0;
        while(gameRun!=1 and gameRun!=2){
            cin>>gameRun;
        }
        cout<<"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    }
    cout<<"Guessing Game script finished.\n\n";
    system("pause");
}
