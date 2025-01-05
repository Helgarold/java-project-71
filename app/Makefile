# Переменные
APP_DIR = ./build/install/app/bin
APP_NAME = app
LIB_DIR = ./build/install/app/lib
RESOURCES_DIR = ./src/main/resources
FILE1_JSON = $(RESOURCES_DIR)/file1.json      # Путь к первому JSON файлу
FILE2_JSON = $(RESOURCES_DIR)/file2.json      # Путь ко второму JSON файлу
FILE1_YAML = $(RESOURCES_DIR)/file1.yml       # Путь к первому YAML файлу
FILE2_YAML = $(RESOURCES_DIR)/file2.yml       # Путь ко второму YAML файлу
FILEPATH1_JSON = $(RESOURCES_DIR)/filepath1.json
FILEPATH2_JSON = $(RESOURCES_DIR)/filepath2.json
format = stylish  # Установите формат вывода по умолчанию

# Цели
.PHONY: all run-json run-yml run-plain run-h clean setup build test lint report install check-updates coverage

# Основная цель
all: run-json

# Запуск приложения с двумя JSON файлами
run-json:
	@echo "Запуск приложения с файлами ${FILE1_JSON} и ${FILE2_JSON}..."
	@echo "Формат: $(format)"
	@${APP_DIR}/$(APP_NAME) --type json -f $(format) ${FILE1_JSON} ${FILE2_JSON}

# Запуск приложения с двумя YAML файлами
run-yml:
	@echo "Запуск приложения с файлами ${FILE1_YAML} и ${FILE2_YAML}..."
	@${APP_DIR}/$(APP_NAME) --type yaml ${FILE1_YAML} ${FILE2_YAML}

# Запуск приложения с форматом plain
run-plain:
	@echo "Запуск приложения с файлами ${FILEPATH1_JSON} и ${FILEPATH2_JSON} в формате plain..."
	@${APP_DIR}/$(APP_NAME) --type json -f plain ${FILEPATH1_JSON} ${FILEPATH2_JSON}

# Запуск приложения с помощью флага -h для вывода справки
run-h:
	@echo "Вывод справки приложения..."
	@${APP_DIR}/$(APP_NAME) -h

# Очистка
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
installDist:
	@echo "Установка приложения..."
	@./gradlew installDist

# Проверка обновлений зависимостей
check-updates:
	@echo "Проверка обновлений зависимостей..."
	@./gradlew dependencyUpdates

# Test Coverage
coverage:
	@echo "Тест покрытия"
	@./gradlew jacocoTestCoverageVerification
