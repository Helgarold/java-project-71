# Цели
.PHONY: build report clean

# Сборка проекта
build:
	make -C app build

# Генерация отчета
report:
	make -C app report

# Очистка проекта
clean:
	make -C app clean

# Запуск приложения с двумя JSON файлами
run:
	make -C app run

# Запуск приложения с флагом -h
run-dist:
	make -C app run-dist

# Установка Gradle Wrapper
setup:
	make -C app setup

# Выполнение тестов
test:
	make -C app test

# Линтинг
lint:
	make -C app lint


