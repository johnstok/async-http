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
package com.johnstok.http.async;


import java.net.InetSocketAddress;


/**
 * A HTTP server.
 *
 * @author Keith Webster Johnston.
 */
public interface Server {

    /**
     * Start the server listening.
     *
     * @param address        The address at which to listen.
     * @param requestFactory The factory for requests.
     */
    void listen(InetSocketAddress address,
                RequestFactory requestFactory);

    /**
     * Start the server listening.
     *
     * @param address        The address at which to listen.
     * @param requestFactory The factory for requests.
     * @param connection     The connection handler.
     */
    void listen(InetSocketAddress address,
                RequestFactory requestFactory,
                Connection connection);


    /**
     * Stop the server listening.
     */
    void close();


    /**
     * Query whether the server is listening for requests.
     *
     * @return True if the server is listening; false otherwise.
     */
    boolean isListening();
}
