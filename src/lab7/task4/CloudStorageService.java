package lab7.task4;

public class CloudStorageService {

    // --- Рольові інтерфейси (ISP) ---

    public interface IFileReader {
        byte[] downloadFile(String name);
    }

    public interface IFileWriter {
        void uploadFile(String name, byte[] data);
        void deleteFile(String name);
    }

    public interface IPublicShareable {
        String generatePublicShareLink(String name);
    }

    public interface IArchiveCompressor {
        void compressArchive(String folderPath);
    }

    public interface INetworkDriveMountable {
        void mountNetworkDrive(String driveLetter);
    }

    // --- Класи-реалізації ---

    public static class ReadOnlyPublicCDNProvider implements IFileReader, IPublicShareable {
        @Override
        public byte[] downloadFile(String name) {
            System.out.println("CDN: Fetching file '" + name + "'...");
            return new byte[]{1, 2, 3};
        }

        @Override
        public String generatePublicShareLink(String name) {
            return "https://cdn.example.com/share/" + name;
        }
    }

    public static class LocalFileSyncProvider implements IFileReader, IFileWriter, IArchiveCompressor {
        @Override
        public void uploadFile(String name, byte[] data) {
            System.out.println("LocalDisk: Uploaded " + name + " (" + data.length + " bytes).");
        }

        @Override
        public byte[] downloadFile(String name) {
            System.out.println("LocalDisk: Reading " + name + " from disk.");
            return new byte[]{4, 5, 6};
        }

        @Override
        public void deleteFile(String name) {
            System.out.println("LocalDisk: Deleted " + name + " from disk.");
        }

        @Override
        public void compressArchive(String folderPath) {
            System.out.println("LocalDisk: Archived folder " + folderPath + ".");
        }
    }

    // --- Клієнтські класи ---

    public static class PublicContentDeliveryService {
        private final IFileReader reader;
        private final IPublicShareable shareable;

        public PublicContentDeliveryService(IFileReader reader, IPublicShareable shareable) {
            this.reader = reader;
            this.shareable = shareable;
        }

        public String deliverPublicResource(String fileName) {
            byte[] content = reader.downloadFile(fileName);
            System.out.println("Delivering resource: " + fileName + " (size: " + content.length + " bytes)");
            return shareable.generatePublicShareLink(fileName);
        }
    }

    public static class BackupService {
        private final IFileWriter writer;
        private final IArchiveCompressor compressor;

        public BackupService(IFileWriter writer, IArchiveCompressor compressor) {
            this.writer = writer;
            this.compressor = compressor;
        }

        public void performBackup(String folderPath, String backupName, byte[] data) {
            writer.uploadFile(backupName, data);
            compressor.compressArchive(folderPath);
        }
    }

    // --- Точка входу для перевірки та демонстрації роботи ---

    public static void main(String[] args) {
        System.out.println("=== Тестування Завдання 4 (ISP) ===");

        // 1. Демонстрація CDN провайдера
        ReadOnlyPublicCDNProvider cdn = new ReadOnlyPublicCDNProvider();
        PublicContentDeliveryService deliveryService = new PublicContentDeliveryService(cdn, cdn);
        String link = deliveryService.deliverPublicResource("presentation.pdf");
        System.out.println("Public Link: " + link);

        System.out.println("-----------------------------------");

        // 2. Демонстрація локального сховища
        LocalFileSyncProvider local = new LocalFileSyncProvider();
        BackupService backupService = new BackupService(local, local);
        backupService.performBackup("/var/data", "backup.zip", new byte[]{10, 20, 30});

        System.out.println("=== Виконано без винятків UnsupportedOperationException ===");
    }
}