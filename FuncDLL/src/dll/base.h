/* base.h*
* Baseado em: 
* http://www.transmissionzero.co.uk/computing/building-dlls-with-mingw/
*/

/* You should define ADD_EXPORTS *only* when building the DLL. */
#ifdef ADD_EXPORTS
  #define ADDAPI __declspec(dllexport)
#else
  #define ADDAPI __declspec(dllimport)
#endif

/* Define calling convention in one place, for convenience. */
#define ADDCALL __cdecl

/* Make sure functions are exported with C linkage under C++ compilers. */

#ifdef __cplusplus
extern "C"
{
#endif

/* Declara nossa função */
ADDAPI int ADDCALL SomaValores(int a, int b);

#ifdef __cplusplus
} // __cplusplus defined.
#endif
