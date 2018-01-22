#include <iostream>
#include <cstdlib>
#include <ctime>
#include <stdio.h>
using namespace std;

int topLeft=0,topCenter=0,topRight=0,middleLeft=0,middleCenter=0,middleRight=0,bottomLeft=0,bottomCenter=0,bottomRight=0,turn=0,restart=1,playerMove=0,compMove=0,playerMoveAccept=0,compMoveAccept=0,win=0,fourCorners=0,care=0,chance=0,difficulty=0,placement=0;


void compAI(){
    care=1;
    srand(time(0));
    chance=((rand()%10)+1);
    if(chance>=difficulty){
        if(middleCenter==1 and topLeft==0 and topRight==0 and bottomLeft==0 and bottomRight==0){
            srand(time(0));
            placement=((rand()%4)+1);
            if(placement==1){topLeft=2;care=0;}
            if(placement==2){topRight=2;care=0;}
            if(placement==3){bottomLeft=2;care=0;}
            if(placement==4){bottomRight=2;care=0;}
        }

        if(fourCorners==1 and care==1){
            if(topLeft==0 and topRight==2 and bottomLeft==2 and bottomRight==2 and care==1){topLeft=2;care=0;}
            if(topLeft==2 and topRight==0 and bottomLeft==2 and bottomRight==2 and care==1){topRight==2;care=0;}
            if(topLeft==2 and topRight==2 and bottomLeft==0 and bottomRight==2 and care==1){bottomLeft=2;care=0;}
            if(topLeft==2 and topRight==2 and bottomLeft==2 and bottomRight==0 and care==1){bottomRight==2;care=0;}
        }
        if(topLeft==2 and topRight==2 and bottomLeft==2 and bottomRight==2 and middleCenter==0 and care==1){middleCenter=2;care=0;}
        if(topCenter==2 and bottomCenter==2 and middleLeft==2 and middleRight==2 and middleCenter==0 and care==1){middleCenter=2;care=0;}
        if(topLeft==2 and topCenter==0 and topRight==2 and middleCenter==2 and bottomCenter==2 and care==1){topCenter=2;care=0;}
        if(bottomLeft==2 and bottomCenter==0 and bottomRight==2 and middleCenter==2 and topCenter==2 and care==1){bottomCenter=0;care=0;}
        if(topLeft==2 and middleLeft==0 and bottomLeft==2 and middleCenter==2 and middleRight==2 and care==1){middleLeft=2;care=0;}
        if(topRight==2 and middleRight==0 and bottomRight==2 and middleCenter==2 and middleLeft==2 and care==1){middleRight=2;care=0;}
        if(topLeft==2 and middleLeft==0 and bottomLeft==2 and care==1){middleLeft=2;care=0;}
        if(topCenter==2 and middleCenter==0 and bottomCenter==2 and care==1){middleCenter=2;care=0;}
        if(topRight==2 and middleRight==0 and bottomRight==2 and care==1){middleRight=2;care=0;}
        if(topLeft==0 and middleLeft==2 and bottomLeft==2 and care==1){topLeft=2;care=0;}
        if(topCenter==0 and middleCenter==2 and bottomCenter==2 and care==1){topCenter=2;care=0;}
        if(topRight==0 and middleRight==2 and bottomRight==2 and care==1){topRight=2;care=0;}
        if(topLeft==2 and middleLeft==2 and bottomLeft==0 and care==1){bottomLeft=2;care=0;}
        if(topCenter==2 and middleCenter==2 and bottomCenter==0 and care==1){bottomCenter=2;care=0;}
        if(topRight==2 and middleRight==2 and bottomRight==0 and care==1){bottomRight=2;care=0;}
        if(topLeft==2 and topCenter==2 and topRight==0 and care==1){topRight=2;care=0;}
        if(middleLeft==2 and middleCenter==2 and middleRight==0 and care==1){middleRight=2;care=0;}
        if(bottomLeft==2 and bottomCenter==2 and bottomRight==0 and care==1){bottomRight=2;care=0;}
        if(topLeft==2 and topCenter==0 and topRight==2 and care==1){topCenter=2;care=0;}
        if(middleLeft==2 and middleCenter==0 and middleRight==2 and care==1){middleCenter=2;care=0;}
        if(bottomLeft==2 and bottomCenter==0 and bottomRight==2 and care==1){bottomCenter=2;care=0;}
        if(topLeft==0 and topCenter==2 and topRight==2 and care==1){topLeft=2;care=0;}
        if(middleLeft==0 and middleCenter==2 and middleRight==2 and care==1){middleLeft=2;care=0;}
        if(bottomLeft==0 and bottomCenter==2 and bottomRight==2 and care==1){bottomLeft=2;care=0;}
        if(topLeft==2 and middleCenter==0 and bottomRight==2 and care==1){middleCenter=2;care=0;}
        if(topLeft==0 and middleCenter==2 and bottomRight==2 and care==1){topLeft=2;care=0;}
        if(topLeft==2 and middleCenter==2 and bottomRight==0 and care==1){bottomRight=2;care=0;}
        if(topRight==2 and middleCenter==0 and bottomLeft==2 and care==1){middleCenter=2;care=0;}
        if(topRight==0 and middleCenter==2 and bottomLeft==2 and care==1){topRight=2;care=0;}
        if(topRight==2 and middleCenter==2 and bottomLeft==0 and care==1){bottomLeft=2;care=0;}

        if(fourCorners==1 and care==1){
            if(topLeft==0 and topRight==1 and bottomLeft==1 and bottomRight==1 and care==1){topLeft=2;care=0;}
            if(topLeft==1 and topRight==0 and bottomLeft==1 and bottomRight==1 and care==1){topRight==2;care=0;}
            if(topLeft==1 and topRight==1 and bottomLeft==0 and bottomRight==1 and care==1){bottomLeft=2;care=0;}
            if(topLeft==1 and topRight==1 and bottomLeft==1 and bottomRight==0 and care==1){bottomRight==2;care=0;}
        }
        if(topLeft==1 and topRight==1 and bottomLeft==1 and bottomRight==1 and middleCenter==0 and care==1){middleCenter=2;care=0;}
        if(topCenter==1 and bottomCenter==1 and middleLeft==1 and middleRight==1 and middleCenter==0 and care==1){middleCenter=2;care=0;}
        if(topLeft==1 and topCenter==0 and topRight==1 and middleCenter==1 and bottomCenter==1 and care==1){topCenter=2;care=0;}
        if(bottomLeft==1 and bottomCenter==0 and bottomRight==1 and middleCenter==1 and topCenter==1 and care==1){bottomCenter=0;care=0;}
        if(topLeft==1 and middleLeft==0 and bottomLeft==1 and middleCenter==1 and middleRight==1 and care==1){middleLeft=2;care=0;}
        if(topRight==1 and middleRight==0 and bottomRight==1 and middleCenter==1 and middleLeft==1 and care==1){middleRight=2;care=0;}
        if(topLeft==1 and middleLeft==0 and bottomLeft==1 and care==1){middleLeft=2;care=0;}
        if(topCenter==1 and middleCenter==0 and bottomCenter==1 and care==1){middleCenter=2;care=0;}
        if(topRight==1 and middleRight==0 and bottomRight==1 and care==1){middleRight=2;care=0;}
        if(topLeft==0 and middleLeft==1 and bottomLeft==1 and care==1){topLeft=2;care=0;}
        if(topCenter==0 and middleCenter==1 and bottomCenter==1 and care==1){topCenter=2;care=0;}
        if(topRight==0 and middleRight==1 and bottomRight==1 and care==1){topRight=2;care=0;}
        if(topLeft==1 and middleLeft==1 and bottomLeft==0 and care==1){bottomLeft=2;care=0;}
        if(topCenter==1 and middleCenter==1 and bottomCenter==0 and care==1){bottomCenter=2;care=0;}
        if(topRight==1 and middleRight==1 and bottomRight==0 and care==1){bottomRight=2;care=0;}
        if(topLeft==1 and topCenter==1 and topRight==0 and care==1){topRight=2;care=0;}
        if(middleLeft==1 and middleCenter==1 and middleRight==0 and care==1){middleRight=2;care=0;}
        if(bottomLeft==1 and bottomCenter==1 and bottomRight==0 and care==1){bottomRight=2;care=0;}
        if(topLeft==1 and topCenter==0 and topRight==1 and care==1){topCenter=2;care=0;}
        if(middleLeft==1 and middleCenter==0 and middleRight==1 and care==1){middleCenter=2;care=0;}
        if(bottomLeft==1 and bottomCenter==0 and bottomRight==1 and care==1){bottomCenter=2;care=0;}
        if(topLeft==0 and topCenter==1 and topRight==1 and care==1){topLeft=2;care=0;}
        if(middleLeft==0 and middleCenter==1 and middleRight==1 and care==1){middleLeft=2;care=0;}
        if(bottomLeft==0 and bottomCenter==1 and bottomRight==1 and care==1){bottomLeft=2;care=0;}
        if(topLeft==1 and middleCenter==0 and bottomRight==1 and care==1){middleCenter=2;care=0;}
        if(topLeft==0 and middleCenter==1 and bottomRight==1 and care==1){topLeft=2;care=0;}
        if(topLeft==1 and middleCenter==1 and bottomRight==0 and care==1){bottomRight=2;care=0;}
        if(topRight==1 and middleCenter==0 and bottomLeft==1 and care==1){middleCenter=2;care=0;}
        if(topRight==0 and middleCenter==1 and bottomLeft==1 and care==1){topRight=2;care=0;}
        if(topRight==1 and middleCenter==1 and bottomLeft==0 and care==1){bottomLeft=2;care=0;}
    }
}


void gameDisplay(){
    if(topLeft==0){cout<<" ";}
    if(topLeft==1){cout<<"X";}
    if(topLeft==2){cout<<"O";}
    cout<<"|";
    if(topCenter==0){cout<<" ";}
    if(topCenter==1){cout<<"X";}
    if(topCenter==2){cout<<"O";}
    cout<<"|";
    if(topRight==0){cout<<" ";}
    if(topRight==1){cout<<"X";}
    if(topRight==2){cout<<"O";}
    cout<<endl;
    cout<<"_____"<<endl;
    if(middleLeft==0){cout<<" ";}
    if(middleLeft==1){cout<<"X";}
    if(middleLeft==2){cout<<"O";}
    cout<<"|";
    if(middleCenter==0){cout<<" ";}
    if(middleCenter==1){cout<<"X";}
    if(middleCenter==2){cout<<"O";}
    cout<<"|";
    if(middleRight==0){cout<<" ";}
    if(middleRight==1){cout<<"X";}
    if(middleRight==2){cout<<"O";}
    cout<<endl;
    cout<<"_____"<<endl;
    if(bottomLeft==0){cout<<" ";}
    if(bottomLeft==1){cout<<"X";}
    if(bottomLeft==2){cout<<"O";}
    cout<<"|";
    if(bottomCenter==0){cout<<" ";}
    if(bottomCenter==1){cout<<"X";}
    if(bottomCenter==2){cout<<"O";}
    cout<<"|";
    if(bottomRight==0){cout<<" ";}
    if(bottomRight==1){cout<<"X";}
    if(bottomRight==2){cout<<"O";}
    cout<<endl<<endl<<endl<<endl<<endl;
}


void playerInput(){
    playerMoveAccept=0;
    while(playerMoveAccept==0){
        playerMove=0;
        while(playerMove!=1 and playerMove!=2 and playerMove!=3 and playerMove!=4 and playerMove!=5 and playerMove!=6 and playerMove!=7 and playerMove!=8 and playerMove!=9 and playerMove!=99){
            cin>>playerMove;
            if(playerMove==1 and topLeft==0){topLeft=1;playerMoveAccept=1;}
            if(playerMove==1 and topLeft==1 and topLeft==2){playerMove=0;}
            if(playerMove==2 and topCenter==0){topCenter=1;playerMoveAccept=1;}
            if(playerMove==2 and topCenter==1 and topCenter==2){playerMove=0;}
            if(playerMove==3 and topRight==0){topRight=1;playerMoveAccept=1;}
            if(playerMove==3 and topRight==1 and topRight==2){playerMove=0;}
            if(playerMove==4 and middleLeft==0){middleLeft=1;playerMoveAccept=1;}
            if(playerMove==4 and middleLeft==1 and middleLeft==2){playerMove=0;}
            if(playerMove==5 and middleCenter==0){middleCenter=1;playerMoveAccept=1;}
            if(playerMove==5 and middleCenter==1 and middleCenter==2){playerMove=0;}
            if(playerMove==6 and middleRight==0){middleRight=1;playerMoveAccept=1;}
            if(playerMove==6 and middleRight==1 and middleRight==2){playerMove=0;}
            if(playerMove==7 and bottomLeft==0){bottomLeft=1;playerMoveAccept=1;}
            if(playerMove==7 and bottomLeft==1 and bottomLeft==2){playerMove=0;}
            if(playerMove==8 and bottomCenter==0){bottomCenter=1;playerMoveAccept=1;}
            if(playerMove==8 and bottomCenter==1 and bottomCenter==2){playerMove=0;}
            if(playerMove==9 and bottomRight==0){bottomRight=1;playerMoveAccept=1;}
            if(playerMove==9 and bottomRight==1 and bottomRight==2){playerMove=0;}
            if(playerMove==99){cout<<"\n\nDo you want to Quit?  1} Yes    2} No\n\n";
                while(playerMove!=1 and playerMove!=2){
                    cin>>playerMove;
                }
                if(playerMove==1){playerMove=99;win=8;}
                if(playerMove==2){playerMove=0;cout<<"\n\n\n\n\n";gameDisplay();}
            }
            cout<<"\n\n\n\n\n";
        }
    }
}


void compInput(){
    compAI();
    compMoveAccept=0;
    if(care==1){
        while(compMoveAccept==0){
            srand(time(0));
            compMove=((rand()%9)+1);
                if(compMove==1 and topLeft==0){topLeft=2;compMoveAccept=1;}
                if(compMove==1 and topLeft==1 and topLeft==2){compMove=0;}
                if(compMove==2 and topCenter==0){topCenter=2;compMoveAccept=1;}
                if(compMove==2 and topCenter==1 and topCenter==2){compMove=0;}
                if(compMove==3 and topRight==0){topRight=2;compMoveAccept=1;}
                if(compMove==3 and topRight==1 and topRight==2){compMove=0;}
                if(compMove==4 and middleLeft==0){middleLeft=2;compMoveAccept=1;}
                if(compMove==4 and middleLeft==1 and middleLeft==2){compMove=0;}
                if(compMove==5 and middleCenter==0){middleCenter=2;compMoveAccept=1;}
                if(compMove==5 and middleCenter==1 and middleCenter==2){compMove=0;}
                if(compMove==6 and middleRight==0){middleRight=2;compMoveAccept=1;}
                if(compMove==6 and middleRight==1 and middleRight==2){compMove=0;}
                if(compMove==7 and bottomLeft==0){bottomLeft=2;compMoveAccept=1;}
                if(compMove==7 and bottomLeft==1 and bottomLeft==2){compMove=0;}
                if(compMove==8 and bottomCenter==0){bottomCenter=2;compMoveAccept=1;}
                if(compMove==8 and bottomCenter==1 and bottomCenter==2){compMove=0;}
                if(compMove==9 and bottomRight==0){bottomRight=2;compMoveAccept=1;}
                if(compMove==9 and bottomRight==1 and bottomRight==2){compMove=0;}
                cout<<"\n\n\n\n\n";
        }
    }
}


void winCheck(){
    if(topLeft==1 and topCenter==1 and topRight==1){win=1;}
    if(middleLeft==1 and middleCenter==1 and middleRight==1){win=1;}
    if(bottomLeft==1 and bottomCenter==1 and bottomRight==1){win=1;}
    if(topLeft==1 and middleLeft==1 and bottomLeft==1){win=1;}
    if(topCenter==1 and middleCenter==1 and bottomCenter==1){win=1;}
    if(topRight==1 and middleRight==1 and bottomRight==1){win=1;}
    if(topLeft==1 and middleCenter==1 and bottomRight==1){win=1;}
    if(topRight==1 and middleCenter==1 and bottomLeft==1){win=1;}
    if(topLeft==1 and bottomLeft==1 and topRight==1 and bottomRight==1 and middleCenter==1){win==5;}
    if(topCenter==1 and bottomCenter==1 and middleLeft==1 and middleRight==1 and middleCenter==1){win=5;}
    if(topLeft==1 and topCenter==1 and topRight==1 and middleCenter==1 and bottomCenter==1){win=5;}
    if(bottomLeft==1 and bottomCenter==1 and bottomRight==1 and middleCenter==1 and topCenter==1){win=5;}
    if(topLeft==1 and middleLeft==1 and bottomLeft==1 and middleCenter==1 and middleRight==1){win=5;}
    if(middleLeft==1 and middleCenter==1 and middleRight==1 and topRight==1 and bottomRight==1){win=5;}
    if(fourCorners==1){
        if(topLeft==1 and bottomLeft==1 and topRight==1 and bottomRight==1){win=7;}
    }

    if(topLeft!=0 and topCenter!=0 and topRight!=0 and middleLeft!=0 and middleCenter!=0 and middleRight!=0 and bottomLeft!=0 and bottomCenter!=0 and bottomRight!=0){win==3;}

    if(topLeft==2 and topCenter==2 and topRight==2){win=2;}
    if(middleLeft==2 and middleCenter==2 and middleRight==2){win=2;}
    if(bottomLeft==2 and bottomCenter==2 and bottomRight==2){win=2;}
    if(topLeft==2 and middleLeft==2 and bottomLeft==2){win=2;}
    if(topCenter==2 and middleCenter==2 and bottomCenter==2){win=2;}
    if(topRight==2 and middleRight==2 and bottomRight==2){win=2;}
    if(topLeft==2 and middleCenter==2 and bottomRight==2){win=2;}
    if(topRight==2 and middleCenter==2 and bottomLeft==2){win=2;}
    if(topLeft==2 and bottomLeft==2 and topRight==2 and bottomRight==2 and middleCenter==2){win=4;}
    if(topCenter==2 and bottomCenter==2 and middleLeft==2 and middleRight==2 and middleCenter==2){win=4;}
    if(topLeft==2 and topCenter==2 and topRight==2 and middleCenter==2 and bottomCenter==2){win=4;}
    if(bottomLeft==2 and bottomCenter==2 and bottomRight==2 and middleCenter==2 and topCenter==2){win=4;}
    if(topLeft==2 and middleLeft==2 and bottomLeft==2 and middleCenter==2 and middleRight==2){win=4;}
    if(middleLeft==2 and middleCenter==2 and middleRight==2 and topRight==2 and bottomRight==2){win=4;}
    if(fourCorners==1){
        if(topLeft==2 and bottomLeft==2 and topRight==2 and bottomRight==2){win=6;}
    }
}


int main(){
    while (restart==1){
        cout<<"What Difficulty do you want?   1} Easy     2} Medium     3} Hard\n\n";
        difficulty=0;
        while(difficulty!=1 and difficulty!=2 and difficulty!=3){
            cin>>difficulty;
        }
        cout<<"\n\n";
        if(difficulty==1){
            difficulty=7;
        }
        if(difficulty==2){
            difficulty=6;
        }
        if(difficulty==3){
            difficulty=0;
        }
        cout<<"NOTE: Type 99 on your turn for an option to quit mid-game.\n\n\n\n\n";
        cout<<"Do you want to play with the extra 'Four Corners' rule?\n(Press '1' for yes or '2' for no.)\n\n";
        fourCorners=0;
        while(fourCorners!=1 and fourCorners!=2){
            cin>>fourCorners;
        }
        cout<<endl<<endl<<endl<<endl<<endl;
        win=0;topLeft=0;topCenter=0;topRight=0;middleLeft=0;middleCenter=0;middleRight=0;bottomLeft=0;bottomCenter=0;bottomRight=0;
        srand(time(0));
        turn=((rand()%2)+1);
        gameDisplay();
        while(win==0){

            while (turn==1 and win==0){
                cout<<"Your turn!\n\n";
                playerInput();
                turn=2;
                gameDisplay();
                winCheck();
            }

            while (turn==2 and win==0){
                cout<<"Computer's turn!\n\n\n\n\n";
                compInput();
                turn=1;
                 gameDisplay();
                winCheck();
            }

        }
        if(win==1){
            cout<<"Hurrah!! You won against your computer!!!\n\n\n";
        }

        if(win==2){
            cout<<"You lost against your own computer!!!\n\n\n";
        }

        if(win==3){
            cout<<"Wow! You tied against your computer!!!!\n\n\n";
        }

        if(win==4){
            cout<<"What the...... Your computer just owned you!!!!!! Your computer got a double win!!!!!!!!!!\n\n\n";
        }

        if(win==5){
            cout<<"Dang! You just double beat your own computer!!! ppppsssshhh So much for Artificial Intelligence hahaha:)\n\n\n";
        }

        if(win==6){
            cout<<"Woah!! Your computer used the Four Corners rule against you!!!\n\n\n";
        }

        if(win==7){
            cout<<"Woah!! You used the Four Corners rule againt your computer!!!\n\n\n";
        }

        if(win==8){cout<<"You quit the game? Hopefully not because of a bug...;D\n\n\n";}

        cout<<"Would you like to retart?\n(Press '1' for yes or '2' for no.)\n";
        restart=0;
        while(restart!=1 and restart!=2){cin>>restart;}
        cout<<"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    }
    cout<<"Tic-Tac-Toe game script finished.\n\n";
    system("pause");
}
