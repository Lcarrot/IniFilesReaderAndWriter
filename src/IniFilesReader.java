import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.LinkedList;

public class IniFilesReader extends InputStream {

    private final FileReader fileReader;

    public IniFilesReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public LinkedList<PairUnifiedGenericsContainer<String, LinkedList<PairUnifiedGenericsContainer<String, String>>>> readIniFile() {
        LinkedList<PairUnifiedGenericsContainer<String, LinkedList<PairUnifiedGenericsContainer<String, String>>>> section = new LinkedList<>();
        try {
            while (fileReader.read() != -1) {
                section.add(readSection());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return section;
    }

    private PairUnifiedGenericsContainer<String, LinkedList<PairUnifiedGenericsContainer<String, String>>> readSection() {
        LinkedList<PairUnifiedGenericsContainer<String, String>> pairs = new LinkedList<>();
        String nameSection = readString();
        try {
            int c = fileReader.read();
            while (c != -1 && c != '[') {
                pairs.add(readPair());
                c = fileReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PairUnifiedGenericsContainer<>(nameSection, pairs);
    }

    public PairUnifiedGenericsContainer<String, String> readPair() {
        String key = readString();
        String value = readString();
        return new PairUnifiedGenericsContainer<>(key, value);
    }

    private String readString() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        try {
            int c = fileReader.read();
            while (c != '"' && c !=']') {
                if (c == '\\') {
                    c = fileReader.read();
                }
                if (c == ' ' && fileReader.read() == '=') {
                    fileReader.read();
                    fileReader.read();
                    break;
                }
                byteBuffer.putChar((char)c);
                c = fileReader.read();
            }
            if (c == '"') {
                fileReader.read();
            }
            byteBuffer.rewind();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(byteBuffer.slice().array());
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return super.skip(n);
    }

    @Override
    public int available() throws IOException {
        return super.available();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset();
    }

    @Override
    public boolean markSupported() {
        return super.markSupported();
    }

    @Override
    public int read() {
        throw new UnsupportedOperationException();
    }
}
