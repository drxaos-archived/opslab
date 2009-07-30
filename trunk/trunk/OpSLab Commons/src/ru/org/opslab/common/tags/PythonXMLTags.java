package ru.org.opslab.common.tags;

/**
 * Класс, содержащий строковые константы с названиями тегов и их параметрами а так же порядок следования параметров в теге.
 */
public interface PythonXMLTags extends XMLTags {

    String TAG_DIRECTORY = "Directory";
    String TAG_DEF = "Def";
    String TAG_ASSIGN = "Assign";
    String TAG_LVALUE = "Lvalue";
    String TAG_RVALUE = "Rvalue";
    String TAG_OPERATOR = "Operator";

    String TAG_PARAM_FROM = "from";
    String TAG_PARAM_ASNAME = "asname";
    String TAG_PARAM_BASE = "base";

    String[] sequence = { TAG_PARAM_NAME, TAG_PARAM_KEY, TAG_PARAM_LANG, TAG_PARAM_PACKAGE, TAG_PARAM_FROM, TAG_PARAM_ASNAME, TAG_PARAM_BASE, TAG_PARAM_TYPE, TAG_PARAM_VALUE, TAG_PARAM_PARAMS };
}
