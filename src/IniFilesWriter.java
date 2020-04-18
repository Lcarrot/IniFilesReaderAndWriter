import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.LinkedList;


public class IniFilesWriter extends OutputStream {

    DataOutputStream outputStream;

    public IniFilesWriter(OutputStream outputStream) {
        this.outputStream = new DataOutputStream(outputStream);
    }

    public void writeIniFile(LinkedList<PairUnifiedGenericsContainer<String, LinkedList<PairUnifiedGenericsContainer<String, String>>>> containers) {
        for (PairUnifiedGenericsContainer<String, LinkedList<PairUnifiedGenericsContainer<String, String>>> section: containers) {
            writeSection(section.getValue1());
            for (PairUnifiedGenericsContainer<String, String> pair: section.getValue2()) {
                writePair(pair);
            }
        }
    }

    private void writeSection(String section) {
        try {
            outputStream.writeChar('[');
            for (int i = 0; i < section.length(); i++) {
                outputStream.writeChar(section.charAt(i));
            }
            outputStream.writeChar(']');
            outputStream.writeChar('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePair(PairUnifiedGenericsContainer<String, String> pair) {
        try {
            writeString(pair.getValue1());
            outputStream.writeChar(' ');
            outputStream.writeChar('=');
            outputStream.writeChar(' ');
            outputStream.writeChar('"');
            writeString(pair.getValue2());
            outputStream.writeChar('"');
            outputStream.writeChar('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeString(String value) {
        try {
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) == '"' || value.charAt(i) == '=' || value.charAt(i) == '\\') {
                    outputStream.writeChar('\\');
                }
                outputStream.writeChar(value.charAt(i));
            }
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
