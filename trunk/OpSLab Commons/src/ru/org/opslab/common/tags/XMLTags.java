package ru.org.opslab.common.tags;

/**
 * Интерфейс содержит строковые константы с именами тегов и именами их параметров.
 */

public interface XMLTags {
    String TAG_PROJECT = "Project";
    String TAG_FILE = "File";
    String TAG_IMPORT = "Import";
    String TAG_CLASS = "Class";
    String TAG_PARAM = "Param";
    String TAG_CALL = "Call";
    String TAG_CONTROL = "Control";
    String TAG_BLOCK = "Block";
    String TAG_LINK = "Link";
    String TAG_STRUCT = "Struct";
    String TAG_VAR = "Var";

    String TAG_PARAM_NAME = "name";
    String TAG_PARAM_KEY = "key";
    String TAG_PARAM_LANG = "lang";
    String TAG_PARAM_PACKAGE = "package";
    String TAG_PARAM_TYPE = "type";
    String TAG_PARAM_VALUE = "value";
    String TAG_PARAM_PARAMS = "params";
    String TAG_PARAM_LINE = "line";
    String TAG_PARAM_POS = "pos";
    String TAG_PARAM_LENGTH = "length";
    String TAG_PARAM_PATH = "path";
    String TAG_PARAM_TARGET = "target";
    String TAG_PARAM_SOURCE = "source";
    String TAG_PARAM_OBJECTID = "objectid";
    String TAG_PARAM_TARGETID = "targetid";
    String TAG_PARAM_SOURCEID = "sourceid";
    String TAG_PARAM_TEXT = "text";
    String TAG_PARAM_TLINE = "tline";
    String TAG_PARAM_TPOS = "tpos";
    String TAG_PARAM_TFILE = "tfile";
    String TAG_PARAM_LABEL = "label";

    String TAG_PARAM_LANG_VAL_JAVA = "Java";

    String TAG_PARAM_TYPE_VAL_CLASS = "class";
    String TAG_PARAM_TYPE_VAL_IMPORT = "import";
    String TAG_PARAM_TYPE_VAL_FIELD = "field";
    String TAG_PARAM_TYPE_VAL_METHOD = "method";
    String TAG_PARAM_TYPE_VAL_TYPE = "type";

    String TAG_PARAM_NAME_VAL_PROJECT_STRUCT = "Project Struct";
    String TAG_PARAM_NAME_VAL_СLASS_DIAGRAM = "Class Diagram";

    String TAG_PARAM_EDGENAME_VAL_EXTENDS = "extends";
    String TAG_PARAM_EDGENAME_VAL_IMPLEMENTS = "implements";
    String TAG_PARAM_EDGENAME_VAL_INNER = "inner";

    String DOT_PARAM_OBJECTID = "URL";

    String OBJECTID_SEPARATOR = ":";

    /** Массив, определяющий стандартную последовательность параметров в теге. */
    String[] paramSequence = { TAG_PARAM_LINE, TAG_PARAM_POS, TAG_PARAM_LENGTH, TAG_PARAM_NAME, TAG_PARAM_PATH, TAG_PARAM_LANG, TAG_PARAM_TYPE, TAG_PARAM_VALUE, TAG_PARAM_PARAMS, TAG_PARAM_KEY,
            TAG_PARAM_OBJECTID };
}
