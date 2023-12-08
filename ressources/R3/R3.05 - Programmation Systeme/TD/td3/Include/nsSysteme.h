/**
 *
 * @File : nsSysteme.h
 *
 * @Synopsis : espace de noms qui contient les prototypes des wrappers
 *             des fonctions systeme
 *
 *
 **/
#if !defined __NSSYSTEME_H__
#define      __NSSYSTEME_H__

#include <cstddef>        // size_t
#include <dirent.h>
#include <unistd.h>       // open(), read(), write(), close()
#include <sys/types.h>    // ssize_t
#include <sys/stat.h>     // struct stat, stat(), fstat()
#include <signal.h>       // struct sigaction, sigset_t



#include "string.h"

#include "CExc.h"

//  Declarations des fonctions concernant les fichiers
//  =========================================================

namespace nsSysteme
{
    void        Stat    (const char * file_name, struct stat * buf);
                             // throw (CExc)
    void        Close  (int fd);
                             // throw (CExc)


    int         Open   (const char * pathname, int flags);
                             // throw (CExc)
    int         Open   (const char * pathname, int flags, ::mode_t mode);
                             // throw (CExc)
    std::size_t Read   (int fd, void * buf, std::size_t count);
                             // throw (CExc)

    void        Stat   (const char * file_name, struct stat * buf);
                             // throw (CExc)

    std::size_t Write  (int fd, const void * buf, std::size_t count);
                             // throw (CExc)

    void        Unlink (const char * pathname);
                             // throw (CExc)

    void        LStat   (const char * file_name, struct stat * buf);
                             // throw (CExc)

    // Fonctions concernant les repertoires
    // ====================================
    // 

    void        ChDir   (const char * path);
                             // throw (CExc)

    void        GetCwd  (char * path, size_t taille);
                             // throw (CExc)

    DIR *       OpenDir (const char * dir_name);
                             // throw (CExc)

    dirent *    ReadDir (DIR * dirStreamP);
                             // throw (CExc)

    void        CloseDir(DIR * dirStreamP);

    // Fonctions concernant les signaux
    // ====================================
    // 
                             // throw (CExc)

    void         Sigaction (int signum, 
                            const struct sigaction * act, 
                            struct sigaction * oldact);
                            // throw (CExc)

    typedef void (* sighandler_t )( int );

    sighandler_t Signal    (int NumSig, sighandler_t NewHandler);
                           // throw (CExc)

    const int CstSigMax = 32;

      
} // nsSysteme



//  Declarations des fonctions shell
//  =========================================================

namespace nsFctShell {
 
  void FileCopy (const char * const Destination,  
		   const char * const Source, 
           const size_t       NbBytes, 
		   const bool         syn = false); 
	       // throw (nsSysteme::CExc)




   void Destroy  (const char * const File);

} // nsFctShell



//  Definitions courtes des fonctions concernant les fichiers
//  =========================================================

inline
void nsSysteme::Stat (const char * file_name, struct stat * buf)
    // throw (CExc)
{
    if (::stat (file_name, buf))
        throw CExc ("stat()", std::string("fichier :")+ file_name);

} // Stat()

inline
void nsSysteme::Close (int fd) // throw (CExc)
{
    if (::close (fd)) throw CExc ("close()", fd);

} // Close()



inline 
std::size_t nsSysteme::Read (int fd, void * buf, std::size_t count)
    // throw (CExc)
{
    ::ssize_t Res;
    if (-1 == (Res = ::read (fd, buf, count)))
        throw CExc ("read()", fd);

    return Res;

} // Read()

inline std::size_t nsSysteme::Write (int fd, const void * buf,
                                     std::size_t count)
    // throw (CExc)
{
    ::ssize_t Res;
    if (-1 == (Res = ::write (fd, buf, count)))
        throw CExc ("write()", fd);

    return Res;

} // Write()

inline 
void nsSysteme::Unlink (const char * pathname)
    // throw (CExc)
{
    if (::unlink (pathname))
        throw CExc ("unlink()", pathname);

} // Unlink()

inline
void nsSysteme::LStat (const char * file_name, struct stat * buf)
    // throw (CExc)
{
    if (::lstat (file_name, buf))
        throw CExc ("lstat()", std::string("fichier :") + file_name);

} // LStat()

//  Definitions courtes des fonctions concernant les repertoires
//  ============================================================

inline
void nsSysteme::ChDir(const char * path) // throw (CExc) 
{
  if(::chdir(path))
    throw CExc ("chdir()", path);
} // ChDir()

inline
void nsSysteme::GetCwd(char * path, size_t taille) // throw (CExc) 
{
  if(::getcwd(path, taille) == 0)
    throw CExc ("getcwd()", path);
} // GetCwd()

inline
DIR *nsSysteme::OpenDir(const char * dir_name) // throw (CExc) 
{
  DIR *pDir;
  if((pDir = ::opendir (dir_name)) == 0) 
        throw CExc ("dir()", dir_name);
  return pDir;
} // Opendir()

inline 
dirent * nsSysteme::ReadDir(DIR * dirStreamP) // throw (CExc) 
{
  errno = 0;
  dirent * pEntry (::readdir(dirStreamP));

  if(pEntry == 0 && errno)
    throw CExc ("readdir()", "");

  return(pEntry);

} // ReadDir()

inline
void  nsSysteme::CloseDir(DIR * dirStreamP) // throw (CExc) 
{
  if(::closedir(dirStreamP)) 
    throw CExc ("closedir()", "");

} // CloseDir()

//  Fonctions concernant les signaux 

inline void nsSysteme::Sigaction (int signum, 
                                  const struct sigaction * act, 
                                  struct sigaction * oldact) 
                                  // throw (CExc) 
{ 
    if (::sigaction (signum, act, oldact)) 
        throw CExc ("sigaction()",""); 

} // Sigaction() 

#endif    /* __NSSYSTEME_H__ */
