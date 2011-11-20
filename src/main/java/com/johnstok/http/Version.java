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


/**
 * The HTTP version.
 *
 * <p>From RFC 2616, section 3.1:
 * <pre>HTTP uses a "<major>.<minor>" numbering scheme to indicate versions
   of the protocol. The protocol versioning policy is intended to allow
   the sender to indicate the format of a message and its capacity for
   understanding further HTTP communication, rather than the features
   obtained via that communication. No change is made to the version
   number for the addition of message components which do not affect
   communication behavior or which only add to extensible field values.
   The <minor> number is incremented when the changes made to the
   protocol add features which do not change the general message parsing
   algorithm, but which may add to the message semantics and imply
   additional capabilities of the sender. The <major> number is
   incremented when the format of a message within the protocol is
   changed. See RFC 2145 [36] for a fuller explanation.

   The version of an HTTP message is indicated by an HTTP-Version field
   in the first line of the message.

       HTTP-Version   = "HTTP" "/" 1*DIGIT "." 1*DIGIT

   Note that the major and minor numbers MUST be treated as separate
   integers and that each MAY be incremented higher than a single digit.
   Thus, HTTP/2.4 is a lower version than HTTP/2.13, which in turn is
   lower than HTTP/12.3. Leading zeros MUST be ignored by recipients and
   MUST NOT be sent.

   An application that sends a request or response message that includes
   HTTP-Version of "HTTP/1.1" MUST be at least conditionally compliant
   with this specification. Applications that are at least conditionally
   compliant with this specification SHOULD use an HTTP-Version of
   "HTTP/1.1" in their messages, and MUST do so for any message that is
   not compatible with HTTP/1.0. For more details on when to send
   specific HTTP-Version values, see RFC 2145 [36].

   The HTTP version of an application is the highest HTTP version for
   which the application is at least conditionally compliant.

   Proxy and gateway applications need to be careful when forwarding
   messages in protocol versions different from that of the application.
   Since the protocol version indicates the protocol capability of the
   sender, a proxy/gateway MUST NOT send a message with a version
   indicator which is greater than its actual version. If a higher
   version request is received, the proxy/gateway MUST either downgrade
   the request version, or respond with an error, or switch to tunnel
   behavior.

   Due to interoperability problems with HTTP/1.0 proxies discovered
   since the publication of RFC 2068[33], caching proxies MUST, gateways
   MAY, and tunnels MUST NOT upgrade the request to the highest version
   they support. The proxy/gateway's response to that request MUST be in
   the same major version as the request.

      Note: Converting between versions of HTTP may involve modification
      of header fields required or forbidden by the versions involved.</pre>
 *
 * @author Keith Webster Johnston.
 */
// TODO: Add 'specification' annotation.
public class Version {

    private final int _major;
    private final int _minor;


    /**
     * Constructor.
     *
     * @param major The major HTTP version.
     * @param minor The minor HTTP version.
     */
    public Version(final int major, final int minor) {
        super();
        _major = major;
        _minor = minor;
    }


    /**
     * Get the major HTTP version.
     *
     * @return Version number as an integer.
     */
    public int major() { // TODO: Rename to getMajor()?
        return _major;
    }


    /**
     * Get the minor HTTP version.
     *
     * @return Version number as an integer.
     */
    public int minor() { // TODO: Rename to getMinor()?
        return _minor;
    }


    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "HTTP/" + _major + "." + _minor;
    }
}
