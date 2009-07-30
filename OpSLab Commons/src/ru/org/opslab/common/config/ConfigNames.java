package ru.org.opslab.common.config;

public interface ConfigNames {

    public interface Source {
        String DIRECTORY = "ru.org.opslab.osv.source.directory";
        String PROJECTNAME = "ru.org.opslab.osv.source.projectname";
        String LANGUAGE = "ru.org.opslab.osv.source.language";
    }

    public interface XmlGenerator {
        String ADDITIONAL = "ru.org.opslab.osv.xmlgenerator.javac";
    }

    public interface SourceXml {
        String MAKE = "ru.org.opslab.osv.sourcexml.make";
        String FILE = "ru.org.opslab.osv.sourcexml.file";
    }

    public interface XmlProcessor {
        String SET = "ru.org.opslab.osv.xmlprocessor.set";
    }

    public interface InterfaceXml {
        String MAKE = "ru.org.opslab.osv.interfacexml.make";
        String FILE = "ru.org.opslab.osv.interfacexml.file";
        String CLASSDIAGRAM = "ru.org.opslab.osv.interfacexml.classdiagram";
        String PROJECTSTRUCT = "ru.org.opslab.osv.interfacexml.projectstruct";
    }
}
