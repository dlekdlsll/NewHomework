package com.example.sixthweek.config;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

public class HTMLCharacterEscapes extends CharacterEscapes {

    private final int[] asciiEscapes;

    private final CharSequenceTranslator translator;

    public HTMLCharacterEscapes() {

        // 1. XSS 방지 처리할 특수 문자 지정
        asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;

        // 2. XSS 방지 처리 특수 문자 인코딩 값 지정
        translator = new AggregateTranslator(
            new LookupTranslator(EntityArrays.BASIC_ESCAPE), // <, >, &, " 는 여기에 포함됨
            new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE),
            new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE)
        );
    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }

    @Override
    public SerializableString getEscapeSequence(int ch) {

        // 커스터마이징 필요하지 않으므로 Apache commons-text에서 제공하는 메서드 사용
        return new SerializedString(StringEscapeUtils.escapeJava(Character.toString((char) ch)));
    }

}
