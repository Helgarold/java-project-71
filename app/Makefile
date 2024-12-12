# Переменные
APP_DIR = ./build/install/app/bin
APP_NAME = app
LIB_DIR = ./build/install/app/lib
RESOURCES_DIR = ./src/main/resources
FILE1 = $(RESOURCES_DIR)/file1.json  # Путь к первому файлу
FILE2 = $(RESOURCES_DIR)/file2.json  # Путь ко второму файлу

# Цели
.PHONY: all run run-dist clean setup build test lint report install check-updates

# Основная цель
all: run

# Запуск приложения с двумя JSON файлами
run:
	@echo "Запуск приложения с файлами ${FILE1} и ${FILE2}..."
	@${APP_DIR}/$(APP_NAME) ${FILE1} ${FILE2}

# Запуск приложения с помощью флага -h для вывода справки
run-dist:
	@echo "Вывод справки приложения..."
	@${APP_DIR}/$(APP_NAME) -h

# Очистка (если нужно добавить логику)
clean:
	@echo "Очистка..."
	@./gradlew clean

# Установка Gradle Wrapper
setup:
	@echo "Настройка Gradle Wrapper..."
	@gradle wrapper --gradle-version 8.8

# Сборка проекта
build:
	@echo "Сборка проекта..."
	@./gradlew build

# Выполнение тестов
test:
	@echo "Запуск тестов..."
	@./gradlew test

# Линтинг
lint:
	@echo "Запуск линтинга..."
	@./gradlew checkstyleMain checkstyleTest

# Генерация отчета
report:
	@echo "Генерация отчета Jacoco..."
	@./gradlew jacocoTestReport

# Установка приложения
install:
	@echo "Установка приложения..."
	@./gradlew clean install

# Проверка обновлений зависимостей
check-updates:
	@echo "Проверка обновлений зависимостей..."
	@./gradlew dependencyUpdates