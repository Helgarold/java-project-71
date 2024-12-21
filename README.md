### Hexlet tests and linter status:
[![Actions Status](https://github.com/Helgarold/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Helgarold/java-project-71/actions)

[![Maintainability](https://api.codeclimate.com/v1/badges/c450f1aa90bca07d7a50/maintainability)](https://codeclimate.com/github/Helgarold/java-project-71/maintainability)

[![Test Coverage](https://api.codeclimate.com/v1/badges/c450f1aa90bca07d7a50/test_coverage)](https://codeclimate.com/github/Helgarold/java-project-71/test_coverage)

## Инструкция по управлению проектом

Этот проект использует `Makefile` для управления различными задачами. Ниже приведены доступные команды и их описание:

### Цели

- `make build`  
  Сборка проекта. Выполняет команду сборки в каталоге `app`.

- `make report`  
  Генерация отчета. Создает отчет в каталоге `app`.

- `make clean`  
  Очистка проекта. Удаляет собранные файлы и временные данные в каталоге `app`.

- `make run-json`  
  Запуск приложения с двумя JSON файлами. Убедитесь, что файлы находятся в правильном каталоге.

- `make run-yml`  
  Запуск приложения с двумя YAML файлами. Убедитесь, что файлы находятся в правильном каталоге.

- `make run-h`  
  Запуск приложения с флагом `-h`. Используйте для получения информации о параметрах командной строки.

- `make setup`  
  Установка Gradle Wrapper. Позволяет установить необходимые зависимости для проекта.

- `make test`  
  Выполнение тестов. Запускает тесты в каталоге `app`.

- `make lint`  
  Линтинг кода. Проверяет код на соответствие стандартам.

### Пример использования

Чтобы собрать проект, выполните команду:
- `make build`

Для генерации отчета используйте:
- `make report`

А чтобы запустить приложение с двумя JSON файлами, выполните:
- `make run-json`

### Установка

Убедитесь, что у вас установлен `make` и необходимые зависимости, прежде чем выполнять команды выше. Также убедитесь, что структура каталогов соответствует ожиданиям (например, наличие папки `app`).

Если у вас есть дополнительные команды или параметры, которые вы хотите добавить, не забудьте указать их в разделе инструкций!

## Пример работы программы
Вот пример работы программы в виде анимации:
[![asciicast](https://asciinema.org/a/dwsggeEJw0j64yeztnscSaKbq.svg)](https://asciinema.org/a/dwsggeEJw0j64yeztnscSaKbq)
