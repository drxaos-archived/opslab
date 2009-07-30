package ru.org.opslab.common.tags;

/**
 * Интерфейс содержит строковые константы с именами тегов и именами их параметров.
 */

public interface JavaXMLTags extends XMLTags {

    String TAG_PACKAGE = "Package";
    String TAG_FIELD = "Field";
    String TAG_ENUMDECL = "EnumDecl";
    String TAG_METHOD = "Method";
    String TAG_VARDECL = "VarDecl";
    String TAG_NEW = "New";

    String TAG_EXTENDS = "Extends";
    String TAG_IMPLEMENTS = "Implements";
    String TAG_TEMPLATE = "Template";
    String TAG_TYPEAPPLY = "TypeApply";
    String TAG_TYPE = "Type";
    String TAG_BOUND = "Bound";
    String TAG_THROWS = "Throws";

    String TAG_PARAM_FULLNAME = "fullname";
    String TAG_PARAM_PREFIX = "prefix";
    String TAG_PARAM_MODIFIERS = "modifiers";
    String TAG_PARAM_TEMPLATE = "template";
    String TAG_PARAM_TYPEAPPLY = "typeapply";
    String TAG_PARAM_EXTENDS = "extends";
    String TAG_PARAM_IMPLEMENTS = "implements";
    String TAG_PARAM_THROWS = "throws";
    String TAG_PARAM_INDEX = "index";
    String TAG_PARAM_RELATE = "relate";

    String TAG_PARAM_TYPE_VAL_INTERFACE = "interface";
    String TAG_PARAM_TYPE_VAL_ENUM = "enum";

    String TAG_PARAM_RELATE_VAL_PROJECT = "project";
    String TAG_PARAM_RELATE_VAL_EXTERNAL = "external";

    String TAG_PARAM_EDGENAME_VAL_EXTENDS = "extends";
    String TAG_PARAM_EDGENAME_VAL_IMPLEMENTS = "implements";
    String TAG_PARAM_EDGENAME_VAL_INNER = "inner";

    /**
     * Массив, тегов, обозначающих использование типов, теги данной группы обязательно должны иметь параметры<br>
     * <... name="" fullname="" line="" pos=""><br>
     * иначе они будут игнорироваться
     */
    String[] GROUP_OF_TYPE = { TAG_EXTENDS, TAG_IMPLEMENTS, TAG_BOUND, TAG_TYPEAPPLY, TAG_TYPE, TAG_THROWS };

    /** Массив, определяющий стандартную последовательность параметров в теге. */
    String[] paramSequence = { TAG_PARAM_LINE, TAG_PARAM_POS, TAG_PARAM_LENGTH, TAG_PARAM_NAME, TAG_PARAM_FULLNAME, TAG_PARAM_PATH, TAG_PARAM_LANG, TAG_PARAM_PREFIX, TAG_PARAM_TYPE, TAG_PARAM_RELATE,
            TAG_PARAM_MODIFIERS, TAG_PARAM_EXTENDS, TAG_PARAM_IMPLEMENTS, TAG_PARAM_TEMPLATE, TAG_PARAM_VALUE, TAG_PARAM_PARAMS, TAG_PARAM_THROWS, TAG_PARAM_KEY, TAG_PARAM_INDEX, TAG_PARAM_OBJECTID };
}
