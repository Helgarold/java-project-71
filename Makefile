# Цели
.PHONY: build report clean run run-h run-json run-yml setup test lint

# Сборка проекта
build:
	make -C app build

# Установка проекта
installDist:
	make -C app installDist

# Генерация отчета
report:
	make -C app report

# Очистка проекта
clean:
	make -C app clean

# Запуск приложения с двумя JSON файлами
run-json:
	make -C app run-json

# Запуск приложения с двумя YAML файлами
run-yml:
	make -C app run-yml

# Запуск приложения с флагом -h
run-h:
	make -C app run-h

# Запуск приложения сравнения plain
run-plain:
	make -C app run-plain
# Установка Gradle Wrapper
setup:
	make -C app setup

# Выполнение тестов
test:
	make -C app test

# Линтинг
lint:
	make -C app lint
