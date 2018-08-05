/*
 * Copyright 2018 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.plaidapp.core.util

import io.plaidapp.core.data.Result
import java.io.IOException

/**
 * Wrap a suspending API [call] in try/catch. In case an exception is thrown, a [Result.Error] is
 * created based on the [message].
 */
suspend fun <T : Any> safeApiCall(message: String, call: suspend () -> Result<T>): Result<T> {
    return try {
        call.invoke()
    } catch (e: Exception) {
        Result.Error(IOException(message, e))
    }
}
