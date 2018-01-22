#include <iostream>
#include <cstdlib>
using namespace std;

void login();
void launcher();

int main()
{
    string user;

    while(1)
    {
        while( user != "saint" && user != "guest" )
        {
            login();

            if ( user != "saint" && user != "guest" )
            {
                cout << " **NOT A USER**" << endl;

                system("PAUSE");
                system("CLS");
            }

            cout << user << endl << endl;

        }

        launcher();

    }

    void login();
}

void login()
{

    string user;

    cout << "\n (*USE 'guest' IF NOT TODD*)\n\n" << endl;
    cout << "   Login: ";

    cin >> user;

    system("CLS");

}

void launcher()
{



}
