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

#include <sys/types.h>    // ssize_t
                                                    
#include <sys/stat.h>     // struct stat, stat(), fstat()

#include <unistd.h>       // open(), read(), write(), close()


#include "string.h"

#include "CExc.h"

//  Declarations des fonctions concernant les fichiers
//  =========================================================

namespace nsSysteme
{
    void        Stat    (const char * file_name, struct stat * buf);
                             // throw (CExc);

    void        Close  (int fd);
                             // throw (CExc);
    int         Open   (const char * pathname, int flags);
                             // throw (CExc);
    int         Open   (const char * pathname, int flags, ::mode_t mode);
                             // throw (CExc);
    std::size_t Read   (int fd, void * buf, std::size_t count);
                             // throw (CExc);
    std::size_t Write  (int fd, const void * buf, std::size_t count);
                             // throw (CExc);     
} // nsSysteme




//  Definitions courtes des fonctions concernant les fichiers
//  =========================================================

inline
void nsSysteme::Stat (const char * file_name, struct stat * buf) // throw (CExc)
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

inline
std::size_t nsSysteme::Write (int fd, const void * buf,
                                     std::size_t count)
    // throw (CExc)
{
  // A completer (exo_01 du TD2
} // Write()

#endif    /* __NSSYSTEME_H__ */

