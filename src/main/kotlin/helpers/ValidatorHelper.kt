package org.delcom.helpers

class ValidatorHelper(private val data: Map<String, Any?>) {
    private val errors = mutableMapOf<String, String>()

    fun required(field: String, message: String) {
        val value = data[field]
        if (value == null || value.toString().trim().isEmpty()) {
            errors[field] = message
        }
    }

    fun minAmount(field: String, min: Long, message: String) {
        // Ubah apapun inputnya menjadi String dulu, lalu ke Long.
        // Ini paling aman untuk data yang datang dari JSON API.
        val value = data[field]?.toString()?.toLongOrNull()

        if (value == null || value < min) {
            errors[field] = message
        }
    }

    fun hasErrors(): Boolean = errors.isNotEmpty()

    fun getErrors(): Map<String, String> = errors

    fun validate() {
        if (hasErrors()) {
            // Biarkan Controller yang menangani responnya
        }
    }
}