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
 * A HTTP request.
 *
 * @author Keith Webster Johnston.
 */
public interface Request {


    /**
     * Called when request begins.
     *
     * @param response The response for this request.
     */
    void onBegin(Response response);


    /**
     * Called when the request line for this request is received.
     *
     * @param method  The method for this request.
     * @param uri     The URI for this request.
     * @param version The version for this request.
     */
    void onRequestLine(String method, String uri, Version version);


    /**
     * Called when the headers for this request are received.
     *
     * @param headers The headers for this request.
     */
    void onHeaders(Map<String, List<String>> headers);

    /**
     * Called when body data is received for this request.
     *
     * @param bytes The body data.
     */
    void onBody(ByteBuffer bytes);

    /**
     * Called when the end of the request is received.
     *
     * @param trailers The trailers for this request; NULL for non-chunked
     *  requests.
     */
    void onEnd(Map<String, List<String>> trailers);
}
