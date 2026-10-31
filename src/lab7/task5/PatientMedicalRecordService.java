package lab7.task5;

public class PatientMedicalRecordService {

    // --- Доменна сутність ---

    public static class MedicalRecord {
        private String recordId;
        private String patientId;
        private String diagnosis;
        private String doctorNotes;

        public MedicalRecord(String recordId, String patientId, String diagnosis, String doctorNotes) {
            this.recordId = recordId;
            this.patientId = patientId;
            this.diagnosis = diagnosis;
            this.doctorNotes = doctorNotes;
        }

        public String getRecordId() { return recordId; }
        public void setRecordId(String recordId) { this.recordId = recordId; }

        public String getPatientId() { return patientId; }
        public void setPatientId(String patientId) { this.patientId = patientId; }

        public String getDiagnosis() { return diagnosis; }
        public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

        public String getDoctorNotes() { return doctorNotes; }
        public void setDoctorNotes(String doctorNotes) { this.doctorNotes = doctorNotes; }
    }

    // --- 1. Абстракції для низькорівневих сервісів (DIP) ---

    public interface IMedicalRecordRepository {
        void insertRecord(MedicalRecord record);
    }

    public interface IEncryptedFileStorage {
        void saveEncryptedRecord(String recordId, String data);
    }

    public interface IEventBusPublisher {
        void publishMedicalEvent(String eventType, String recordId);
    }

    // --- 2. Конкретні Production-реалізації інфраструктури ---

    public static class OracleHealthDatabase implements IMedicalRecordRepository {
        @Override
        public void insertRecord(MedicalRecord record) {
            System.out.println("[Oracle Health DB] Запис '" + record.getRecordId() + "' для пацієнта '" + record.getPatientId() + "' успішно збережено в таблицю 'PATIENT_RECORDS'.");
        }
    }

    public static class EncryptedLocalFileStorage implements IEncryptedFileStorage {
        @Override
        public void saveEncryptedRecord(String recordId, String data) {
            System.out.println("[Encrypted File Storage] Картку '" + recordId + "' зашифровано AES-256 та збережено у локальне сховище.");
        }
    }

    public static class HospitalEventBusPublisher implements IEventBusPublisher {
        @Override
        public void publishMedicalEvent(String eventType, String recordId) {
            System.out.println("[Hospital EventBus] Опубліковано подію '" + eventType + "' для медичного запису '" + recordId + "'.");
        }
    }

    // --- 3. Mock/Test-реалізації для демонстрації підміни залежностей ---

    public static class MockMedicalRecordRepository implements IIMedicalRecordRepositoryMock {
        @Override
        public void insertRecord(MedicalRecord record) {
            System.out.println("[Mock DB] Симуляція збереження в пам'ять (In-Memory) запису: " + record.getRecordId());
        }
    }

    private interface IIMedicalRecordRepositoryMock extends IMedicalRecordRepository {}

    public static class MockFileStorage implements IEncryptedFileStorage {
        @Override
        public void saveEncryptedRecord(String recordId, String data) {
            System.out.println("[Mock Storage] Фіктивний запис у пам'ять без шифрування: " + recordId);
        }
    }

    public static class MockEventBusPublisher implements IEventBusPublisher {
        @Override
        public void publishMedicalEvent(String eventType, String recordId) {
            System.out.println("[Mock EventBus] Подію '" + eventType + "' зафіксовано в локальний тестовий лог.");
        }
    }

    // --- 4. Високорівневий сервіс бізнес-логіки (залежить виключно від абстракцій) ---

    public static class RecordManager {
        private final IEncryptedFileStorage fileStorage;
        private final IMedicalRecordRepository repository;
        private final IEventBusPublisher eventBusPublisher;

        // Впровадження залежностей через конструктор (Constructor Injection)
        public RecordManager(IEncryptedFileStorage fileStorage,
                             IMedicalRecordRepository repository,
                             IEventBusPublisher eventBusPublisher) {
            this.fileStorage = fileStorage;
            this.repository = repository;
            this.eventBusPublisher = eventBusPublisher;
        }

        public void savePatientRecord(MedicalRecord record) {
            if (record == null || record.getPatientId() == null || record.getPatientId().isEmpty()) {
                throw new IllegalArgumentException("Некоректний медичний запис пацієнта.");
            }

            this.repository.insertRecord(record);
            this.fileStorage.saveEncryptedRecord(record.getRecordId(), record.getDiagnosis());
            this.eventBusPublisher.publishMedicalEvent("PATIENT_DIAGNOSIS_UPDATED", record.getRecordId());
        }
    }

    // --- 5. Точка збірки (Composition Root) ---

    public static void main(String[] args) {
        MedicalRecord sampleRecord = new MedicalRecord("REC-7041", "PAT-1082", "Гострий бронхіт", "Призначено інгаляції");

        System.out.println("=== 1. Робота через Production-інфраструктуру (Oracle + LocalStorage + EventBus) ===");
        // Manual DI / Composition Root: створення залежностей та впровадження
        IMedicalRecordRepository prodDb = new OracleHealthDatabase();
        IEncryptedFileStorage prodStorage = new EncryptedLocalFileStorage();
        IEventBusPublisher prodBus = new HospitalEventBusPublisher();

        RecordManager prodService = new RecordManager(prodStorage, prodDb, prodBus);
        prodService.savePatientRecord(sampleRecord);

        System.out.println("\n=== 2. Робота через Mock-інфраструктуру для Unit-тестів ===");
        // Підміна залежностей без зміни жодного рядка в RecordManager
        IMedicalRecordRepository mockDb = new MockMedicalRecordRepository();
        IEncryptedFileStorage mockStorage = new MockFileStorage();
        IEventBusPublisher mockBus = new MockEventBusPublisher();

        RecordManager testService = new RecordManager(mockStorage, mockDb, mockBus);
        testService.savePatientRecord(sampleRecord);

        System.out.println("\n=== Тест завершено успішно: DIP дотримано ===");
    }
}