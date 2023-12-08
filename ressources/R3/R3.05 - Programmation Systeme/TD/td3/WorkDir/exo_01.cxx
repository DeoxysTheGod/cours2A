/**
 *
 * @File : 
 *
 * @Author : A. B. Dragut
 *
 * @Synopsis : modelmain
 **/

#include <string>
#include <exception>
#include <iostream>    
#include <iomanip>
#include <unistd.h>     // getdtablesize()
#include <sys/time.h>   // fd_set

#include "nsSysteme.h"
#include "CExc.h"

using namespace nsSysteme;
using namespace std;

namespace {
  void Derout(int NumSig) {
    cout << "Signal " << strsignal(NumSig) << " numéro " << NumSig<< endl;
  }
}

int main(int argc, char * argv [])
{
    cout << "start" << endl;
  try {
    if (2 != argc ||
    (argv[1][0] != 'D'&& argv[1][0] != 'I' && argv[1][0] != 'P'))
        throw CExc ("main() ", string("Usage: ") + argv[0] != + "{P|I|D}");
        
    for (int NumSig = 1; NumSig < CstSigMax; ++NumSig) {
        if (NumSig == SIGKILL || NumSig == SIGSTOP) {
            
            switch(argv[1][0]) {
                case 'P':
                    Signal(NumSig, Derout);
                    break;
                case 'I':
                    Signal(NumSig, SIG_IGN);
                    break;
                case 'D':
                    Signal(NumSig, SIG_DFL);
                    break;
                default:
                    break;
            }
        } else {
        
            cout << "error" << endl;
        }
    }
    cout << getpid() << endl;
    for(;;);

///code
    return 0;
  }
  catch (const CExc & Exc) {
        cerr <<Exc<< endl;
        return errno;
  }
  catch (const exception & Exc) {
        cerr << "Exception : " << Exc.what () << endl;
        return 1;
  }
  catch (...) {
        cerr << "Exception inconnue recue dans la fonction main()" 
             << endl;
        return 1;
  }


}  //  main()

