import java.io.*;
import java.util.LinkedList;


public class IniFilesWriter extends OutputStream {
    private final FileWriter fileWriter;

    public IniFilesWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void writeIniFile(LinkedList<PairUnifiedGenericsContainer<String, LinkedList<PairUnifiedGenericsContainer<String, String>>>> containers) {
        for (PairUnifiedGenericsContainer<String, LinkedList<PairUnifiedGenericsContainer<String, String>>> section : containers) {
            writeSection(section.getValue1());
            for (PairUnifiedGenericsContainer<String, String> pair : section.getValue2()) {
                writePair(pair);
            }
        }
    }

    private void writeSection(String section) {
        try {
            fileWriter.write('[');
            for (int i = 0; i < section.length(); i++) {
                if (section.charAt(i) == '=' || section.charAt(i) == '\\' || section.charAt(i) == '"') {
                    fileWriter.write('\\');
                }
                fileWriter.write(section.charAt(i));
            }
            fileWriter.write(section);
            fileWriter.write("]\\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePair(PairUnifiedGenericsContainer<String, String> pair) {
        try {
            writeString(pair.getValue1());
            fileWriter.write(" = \"");
            writeString(pair.getValue2());
            fileWriter.write("\"\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeString(String value) {
        try {
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) == '"' || value.charAt(i) == '=' || value.charAt(i) == '\\') {
                    fileWriter.write('\\');
                }
                fileWriter.write(value.charAt(i));
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public void write(int b) {
        throw new UnsupportedOperationException();
    }
}
