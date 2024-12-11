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
