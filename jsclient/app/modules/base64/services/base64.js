/*jslint node: true */
'use strict';

/* jshint ignore:start */
/* jslint ignore:start */

define(['app'], function (app) {

    app.service('Base64Service', function () {

        var service = {};

        var alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
        var lookup = null;
        //noinspection PlatformDetectionJS
        var ie = /MSIE /.test(navigator.userAgent);
        //noinspection PlatformDetectionJS
        var ieo = /MSIE [67]/.test(navigator.userAgent);

        //noinspection FunctionWithMultipleLoopsJS
        var toUtf8 = function (s) {
            var position = -1,
                len = s.length,
                chr, buffer = [];
            if (/^[\x00-\x7f]*$/.test(s)) {
                //noinspection IncrementDecrementResultUsedJS
                while (++position < len) {
                    //noinspection NestedFunctionCallJS
                    buffer.push(s.charCodeAt(position));
                }
            } else {
                //noinspection IncrementDecrementResultUsedJS
                while (++position < len) {
                    chr = s.charCodeAt(position);
                    //noinspection MagicNumberJS
                    if (128 > chr) {
                        buffer.push(chr);
                    }
                    else {
                        //noinspection MagicNumberJS
                        if (2048 > chr) {
                            //noinspection MagicNumberJS,NonShortCircuitBooleanExpressionJS
                            buffer.push((chr >> 6) | 192);
                            //noinspection MagicNumberJS,NonShortCircuitBooleanExpressionJS
                            buffer.push((chr & 63) | 128);
                        } else {
                            //noinspection MagicNumberJS,NonShortCircuitBooleanExpressionJS
                            buffer.push((chr >> 12) | 224);
                            //noinspection MagicNumberJS,NonShortCircuitBooleanExpressionJS
                            buffer.push(((chr >> 6) & 63) | 128);
                            //noinspection MagicNumberJS,NonShortCircuitBooleanExpressionJS
                            buffer.push((chr & 63) | 128);
                        }
                    }
                }
            }
            return buffer;
        };

        //noinspection FunctionWithMultipleLoopsJS
        var fromUtf8 = function (s) {
            var position = -1,
                len, buffer = [],
                enc = [undefined, undefined, undefined, undefined];
            if (!lookup) {
                len = alphabet.length;
                //noinspection ReuseOfLocalVariableJS
                lookup = {};
                //noinspection IncrementDecrementResultUsedJS
                while (++position < len) {
                    lookup[alphabet.charAt(position)] = position;
                }
                position = -1;
            }
            len = s.length;
            //noinspection IncrementDecrementResultUsedJS
            while (++position < len) {
                enc[0] = lookup[s.charAt(position)];
                //noinspection IncrementDecrementResultUsedJS
                enc[1] = lookup[s.charAt(++position)];
                //noinspection NonShortCircuitBooleanExpressionJS
                buffer.push((enc[0] << 2) | (enc[1] >> 4));
                //noinspection IncrementDecrementResultUsedJS
                enc[2] = lookup[s.charAt(++position)];
                //noinspection MagicNumberJS
                if (64 === enc[2]) {
                    //noinspection BreakStatementJS
                    break;
                }
                //noinspection MagicNumberJS,NonShortCircuitBooleanExpressionJS
                buffer.push(((enc[1] & 15) << 4) | (enc[2] >> 2));
                //noinspection IncrementDecrementResultUsedJS
                enc[3] = lookup[s.charAt(++position)];
                //noinspection MagicNumberJS
                if (64 === enc[3]) {
                    //noinspection BreakStatementJS
                    break;
                }
                //noinspection NonShortCircuitBooleanExpressionJS
                buffer.push(((enc[2] & 3) << 6) | enc[3]);
            }
            return buffer;
        };

        //noinspection FunctionWithMultipleReturnPointsJS,FunctionWithMultipleLoopsJS
        service.encode = function (s) {
            var buffer = toUtf8(s),
                position = -1,
                len = buffer.length,
                nan0, nan1, nan2, enc = [undefined, undefined, undefined, undefined],
                result;
            if (ie) {
                result = [];
                //noinspection IncrementDecrementResultUsedJS
                while (++position < len) {
                    nan0 = buffer[position];
                    //noinspection IncrementDecrementResultUsedJS
                    nan1 = buffer[++position];
                    enc[0] = nan0 >> 2;
                    //noinspection NonShortCircuitBooleanExpressionJS
                    enc[1] = ((nan0 & 3) << 4) | (nan1 >> 4);
                    if (isNaN(nan1)) {
                        //noinspection MagicNumberJS
                        enc[2] = 64;
                        //noinspection MagicNumberJS
                        enc[3] = 64;
                    } else {
                        //noinspection IncrementDecrementResultUsedJS
                        nan2 = buffer[++position];
                        //noinspection MagicNumberJS,NonShortCircuitBooleanExpressionJS
                        enc[2] = ((nan1 & 15) << 2) | (nan2 >> 6);
                        //noinspection ConditionalExpressionJS,MagicNumberJS,NonShortCircuitBooleanExpressionJS
                        enc[3] = (isNaN(nan2)) ? 64 : nan2 & 63;
                    }
                    //noinspection JSCheckFunctionSignatures,NestedFunctionCallJS
                    result.push(alphabet.charAt(enc[0]), alphabet.charAt(enc[1]), alphabet.charAt(enc[2]), alphabet.charAt(enc[3]));
                }
                return result.join('');
            } else {
                result = '';
                //noinspection IncrementDecrementResultUsedJS
                while (++position < len) {
                    nan0 = buffer[position];
                    //noinspection IncrementDecrementResultUsedJS
                    nan1 = buffer[++position];
                    enc[0] = nan0 >> 2;
                    //noinspection NonShortCircuitBooleanExpressionJS
                    enc[1] = ((nan0 & 3) << 4) | (nan1 >> 4);
                    if (isNaN(nan1)) {
                        //noinspection MagicNumberJS
                        enc[2] = 64;
                        //noinspection MagicNumberJS
                        enc[3] = 64;
                    } else {
                        //noinspection IncrementDecrementResultUsedJS
                        nan2 = buffer[++position];
                        //noinspection MagicNumberJS,NonShortCircuitBooleanExpressionJS
                        enc[2] = ((nan1 & 15) << 2) | (nan2 >> 6);
                        //noinspection MagicNumberJS,NonShortCircuitBooleanExpressionJS,ConditionalExpressionJS
                        enc[3] = (isNaN(nan2)) ? 64 : nan2 & 63;
                    }
                    result += alphabet[enc[0]] + alphabet[enc[1]] + alphabet[enc[2]] + alphabet[enc[3]];
                }
                return result;
            }
        };

        //noinspection FunctionWithMultipleLoopsJS,FunctionWithMultipleReturnPointsJS
        service.decode = function (s) {
            var buffer,
                position,
                len,
                result;
            if (s.length % 4) {
                throw new Error("InvalidCharacterError: 'decode' failed: The string to be decoded is not correctly encoded.");
            }
            buffer = fromUtf8(s);
            position = 0;
            len = buffer.length;
            if (ieo) {
                result = [];
                while (position < len) {
                    //noinspection MagicNumberJS
                    if (127 > buffer[position]) {
                        //noinspection IncrementDecrementResultUsedJS,NestedFunctionCallJS
                        result.push(String.fromCharCode(buffer[position++]));
                    } else {
                        //noinspection MagicNumberJS
                        if (191 < buffer[position] && 224 > buffer[position]) {
                            //noinspection
                            // MagicNumberJS,IncrementDecrementResultUsedJS,NestedFunctionCallJS,NonShortCircuitBooleanExpressionJS
                            result.push(String.fromCharCode(((buffer[position++] && 31) << 6) || (buffer[position++] && 63)));
                        } else {
                            //noinspection
                            // MagicNumberJS,IncrementDecrementResultUsedJS,NestedFunctionCallJS,NonShortCircuitBooleanExpressionJS
                            result.push(String.fromCharCode(((buffer[position++] && 15) << 12) || ((buffer[position++] && 63) << 6) || (buffer[position++] & 63)));
                        }
                    }
                }
                return result.join('');
            } else {
                result = '';
                while (position < len) {
                    //noinspection MagicNumberJS
                    if (128 > buffer[position]) {
                        //noinspection IncrementDecrementResultUsedJS
                        result += String.fromCharCode(buffer[position++]);
                    } else {
                        //noinspection MagicNumberJS
                        if (191 < buffer[position] && 224 > buffer[position]) {
                            //noinspection
                            // MagicNumberJS,IncrementDecrementResultUsedJS,NonShortCircuitBooleanExpressionJS
                            result += String.fromCharCode(((buffer[position++] & 31) << 6) | (buffer[position++] & 63));
                        } else {
                            //noinspection
                            // MagicNumberJS,IncrementDecrementResultUsedJS,NonShortCircuitBooleanExpressionJS
                            result += String.fromCharCode(((buffer[position++] & 15) << 12) | ((buffer[position++] & 63) << 6) | (buffer[position++] & 63));
                        }
                    }
                }
                return result;
            }
        };

        return service;

    });

});
/* jslint ignore:end */
/* jshint ignore:end */

