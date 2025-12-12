#include "lz_string_bridge.h"
#include "libs/lz-string-cpp/src/lz-string.hpp"
#include <string>
#include <vector>
#include <cstdlib>
#include <cstring>

extern "C" {

char* bridge_compress_to_base64(const uint16_t* input, size_t input_len) {
    if (!input) {
        return NULL;
    }

    try {
        // 1. Create a C++ u16string from the raw C-style input.
        std::u16string u16_input(reinterpret_cast<const char16_t*>(input), input_len);

        // 2. Compress the string using the C++ library.
        // The result of compressToBase64 is a u16string, but it only contains ASCII characters.
        std::u16string u16_compressed = lzstring::compressToBase64(u16_input);

        // 3. Convert the u16string result to a standard string (UTF-8).
        std::string compressed_str;
        compressed_str.reserve(u16_compressed.length());
        for (char16_t c : u16_compressed) {
            compressed_str += static_cast<char>(c);
        }

        // 4. Allocate memory for the C-style string and copy the result.
        char* result = (char*)malloc(compressed_str.length() + 1);
        if (!result) {
            return NULL;
        }
        strcpy(result, compressed_str.c_str());
        return result;

    } catch (...) {
        return NULL;
    }
}


uint16_t* bridge_decompress_from_base64(const char* input, size_t* output_len) {
    if (!input || !output_len) {
        return NULL;
    }

    try {
        // 1. Convert the input C-string to a C++ u16string (required by the library).
        std::string input_str(input);
        std::u16string u16_input;
        u16_input.reserve(input_str.length());
        for (char c : input_str) {
            u16_input += static_cast<char16_t>(c);
        }

        // 2. Decompress the string using the C++ library.
        std::u16string u16_decompressed = lzstring::decompressFromBase64(u16_input);

        // 3. Allocate memory for the C-style uint16_t array and copy the result.
        size_t len = u16_decompressed.length();
        // The result from C++ is not null-terminated, but JNI's NewString short* takes a length.
        uint16_t* result = (uint16_t*)malloc(len * sizeof(uint16_t));
        if (!result) {
            return NULL;
        }
        memcpy(result, u16_decompressed.c_str(), len * sizeof(uint16_t));
        
        *output_len = len;
        return result;

    } catch (...) {
        *output_len = 0;
        return NULL;
    }
}

} // extern "C"
