/*-----------------------------------------------------------------------------
 * Copyright © 2011 Keith Webster Johnston.
 * All rights reserved.
 *
 * This file is part of async-http.
 *
 * async-http is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * async-http is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with async-http. If not, see <http://www.gnu.org/licenses/>.
 *---------------------------------------------------------------------------*/
package com.johnstok.http;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;


/**
 * A HTTP Response.
 *
 * <p>The response methods must be called in the correct sequence, as follows:
 * <ol>
 *   <li>{@link #writeStatusLine(Version, int, String)} (exactly once).</li>
 *   <li>{@link #writeHeaders(Map)} (exactly once).</li>
 *   <li>{@link #writeBody(ByteBuffer)} (zero or more times).</li>
 *   <li>{@link #writeEnd(Map)} (exactly once).</li>
 * </ol>
 * If a response method is called incorrectly an {@link IllegalStateException}
 * will be thrown.
 *
 * @author Keith Webster Johnston.
 */
public interface Response {


    /**
     * Write the HTTP status line for this response.
     *
     * @param version      The HTTP version.
     * @param statusCode   The HTTP status code.
     * @param reasonPhrase The HTTP reason phrase.
     *
     * @throws IllegalStateException If called out of sequence; if called more
     *  than once.
     */
    void writeStatusLine(Version version,
                         int statusCode,
                         String reasonPhrase);


    /**
     * Write the headers for this response.
     *
     * @param headers The HTTP headers.
     *
     * @throws IllegalStateException If called out of sequence; if called more
     *  than once.
     */
    void writeHeaders(Map<String, ? extends List<String>> headers);


    /**
     * Write body data for this response.
     *
     * @param bytes The body data to write.
     *
     * @throws IllegalStateException If called out of sequence.
     */
    void writeBody(ByteBuffer bytes);


    /**
     * Write the end of the response.
     *
     * @param trailers The response trailers (only relevant for chunked
     *  responses).
     *
     * @throws IllegalStateException If called out of sequence; if called more
     *  than once.
     */
    void writeEnd(Map<String, ? extends List<String>> trailers);
}
