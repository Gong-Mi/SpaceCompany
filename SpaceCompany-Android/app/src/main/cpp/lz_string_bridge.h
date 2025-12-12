#ifndef LZ_STRING_BRIDGE_H
#define LZ_STRING_BRIDGE_H

#include <stddef.h>
#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

/**
 * Compresses a UTF-16 string to a Base64 encoded string.
 *
 * @param input The raw UTF-16 string data.
 * @param input_len The number of characters (uint16_t) in the input string.
 * @return A new heap-allocated, null-terminated C-string (char*) with the Base64 result.
 *         The caller is responsible for freeing this memory with free().
 *         Returns NULL on failure.
 */
char* bridge_compress_to_base64(const uint16_t* input, size_t input_len);

/**
 * Decompresses a Base64 encoded string to a UTF-16 string.
 *
 * @param input A null-terminated C-string (char*) containing the Base64 data.
 * @param output_len A pointer to a size_t variable that will receive the number of characters
 *                   (uint16_t) in the output string.
 * @return A new heap-allocated, null-terminated UTF-16 string (uint16_t*) with the result.
 *         The caller is responsible for freeing this memory with free().
 *         Returns NULL on failure.
 */
uint16_t* bridge_decompress_from_base64(const char* input, size_t* output_len);

#ifdef __cplusplus
}
#endif

#endif // LZ_STRING_BRIDGE_H
