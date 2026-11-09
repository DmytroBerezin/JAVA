module order.storage {
    requires transitive java.logging;
    requires static lombok;
    requires javafaker;

    exports com.example.order.storage.model;
    exports com.example.order.storage.repository;
    exports com.example.order.storage.exception;
}