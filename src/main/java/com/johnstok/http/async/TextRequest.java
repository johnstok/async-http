/*-----------------------------------------------------------------------------
 * Copyright © 2011 Keith Webster Johnston.
 * All rights reserved.
 *
 * This file is part of wm4j.
 *
 * wm4j is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * wm4j is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with wm4j. If not, see <http://www.gnu.org/licenses/>.
 *---------------------------------------------------------------------------*/
package com.johnstok.http.async;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.List;
import java.util.Map;


/**
 * TODO: Describe this classes responsibility.
 *
 * TODO: Add a description for this type.
 *
 * @author Keith Webster Johnston.
 */
public abstract class TextRequest
    implements
        Request {


    private static final ByteBuffer EMPTY     = ByteBuffer.wrap(new byte[]{});

    private ByteBuffer             underflow = EMPTY;
    private final Charset          encoding  = Charset.forName("utf-8");
    private final CharsetDecoder   decoder   = encoding.newDecoder();
    private final CharBuffer       text      = CharBuffer.allocate(2); // TODO: Choose a more sensible value - see averageCharsPerByte().

    public TextRequest() {
        decoder.onMalformedInput(CodingErrorAction.REPLACE);
        decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
    }


    /** {@inheritDoc} */
    @Override
    public void onBody(final ByteBuffer data) {
        final ByteBuffer joined = join(underflow, data);
        CoderResult coderResult;
        do {
            coderResult = decoder.decode(joined, text, false);
            text.flip();
            onText(text);
            text.clear();
        } while (coderResult.isOverflow());
        underflow = joined.slice();
    }


    /** {@inheritDoc} */
    @Override
    public void onEnd(final Map<String, List<String>> trailers) {
        decoder.decode(EMPTY, text, true);
        // Do while 'underflow' is not returned
        decoder.flush(text);
        System.out.print(text.flip().toString());
    }


    public abstract void onText(final CharBuffer text);


    private final ByteBuffer join(final ByteBuffer b, final ByteBuffer wrap) {
        final int size = b.remaining() + wrap.remaining();
        final ByteBuffer joined = ByteBuffer.allocate(size);
        joined.put(b);
        joined.put(wrap);
        joined.flip();
        return joined;
    }

}
