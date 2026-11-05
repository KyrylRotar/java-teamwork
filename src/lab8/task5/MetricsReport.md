# Завдання 5. Метрики якості дизайну (варіант 4)

**Варіант:** публічна або університетська бібліотека. Розрахунки виконані для legacy-прикладу `LegacyLibraryProcessor` з `variants-java.xml` і фактичної реалізації завдань 2–4 у `src/lab8/task2/`, `src/lab8/task3/` та `src/lab8/task4/`. У таблицях використано повні назви однойменних класів із різних пакетів.

## Методика

Для CBO рахується кількість унікальних типів проєкту, на які клас посилається через поля, параметри, результати методів, виклики, створення об'єктів або реалізацію інтерфейсів. Типи Java, примітиви та `String` не враховуються. Для LCOM4 вершинами є бізнес-методи; конструктори та звичайні accessors/getters виключені, ребро додається за спільним полем або прямим викликом іншого методу. Якщо клас не має бізнес-методів, LCOM4 позначено «—».

## CBO до та після рефакторингу

| Етап | Клас | Пов'язані типи проєкту | CBO |
|---|---|---|---:|
| До | `LegacyLibraryProcessor` | `Reader`, `Book`, `LoanRecord` | 3 |
| До | `LoanRecord` | `Reader`, `Book` | 2 |
| Після | `lab8.task2.Reader` | — | 0 |
| Після | `lab8.task2.Book` | `lab8.task2.BookCategory` | 1 |
| Після | `lab8.task2.LoanRecord` | `lab8.task2.Reader`, `lab8.task2.Book` | 2 |
| Після | `lab8.task3.LoanController` | `IAuditLogger`, `IEmailNotifier`, `LoanTermsPolicy`, `LoanRequestDTO`, `LoanReceiptDTO`, `lab8.task2.BookCategory`, `lab8.task2.Book`, `lab8.task2.Reader`, `lab8.task2.LoanRecord`, `lab8.task4.BookCategory`, `lab8.task4.Book`, `LoanTerms` | 12 |
| Після | `lab8.task4.LoanPolicyRegistry` | `lab8.task4.BookCategory`, `LoanTermsPolicy`, `lab8.task4.Book`, `LoanTerms` | 4 |
| Після | `lab8.task4.StandardLoanTermsPolicy` | `LoanTermsPolicy`, `lab8.task4.Book`, `lab8.task4.BookCategory`, `LoanTerms` | 4 |
| Після | `lab8.task4.RareLoanTermsPolicy` | `LoanTermsPolicy`, `lab8.task4.Book`, `lab8.task4.BookCategory`, `LoanTerms` | 4 |
| Після | `lab8.task4.AcademicLoanTermsPolicy` | `LoanTermsPolicy`, `lab8.task4.Book`, `lab8.task4.BookCategory`, `LoanTerms` | 4 |
| Після | `lab8.task4.LoanTerms` | — | 0 |

**Висновок за CBO:** доменні класи та політики відповідають цільовому `CBO ≤ 4`. Однак інтеграційний `LoanController` має `CBO = 12`, тобто ціль для контролера поки не досягнута. Причина — контролер напряму координує DTO, аудит, email, доменні сутності Task 2 та паралельні `Book`/`BookCategory` Task 4. Це виміряний стан наявного коду, а не припущення, що метрика покращилася. Для досягнення порогу потрібно зменшити відповідальність контролера і усунути дублювання доменних типів між Task 2 та Task 4.

Числовий CBO legacy-класу `LegacyLibraryProcessor` становить 3, тому його не можна коректно описати як зменшений після рефакторингу. Рефакторинг ізолював правила категорій у стратегіях та знизив CBO доменних сутностей, але інтегрований контролер потребує окремого доопрацювання.

## LCOM4 після рефакторингу

| Клас | Зв'язність бізнес-методів | LCOM4 |
|---|---|---:|
| `LegacyLibraryProcessor` (до) | Один монолітний `issueBookFromConsole`; метрика не показує змішані обов'язки всередині одного методу | 1 |
| `lab8.task2.Reader` | `canBorrowMoreBooks` та `incrementLoans` спільно змінюють/читають `activeLoansCount` | 1 |
| `lab8.task2.Book` | Немає бізнес-методів; лише дані та accessors | — |
| `lab8.task2.LoanRecord` | Бізнес-розрахунок підсумкової суми утворює одну зв'язну компоненту | 1 |
| `lab8.task3.LoanController` | Один use-case метод `processLoan` | 1 |
| `lab8.task4.LoanPolicyRegistry` | Один бізнес-метод `calculate` | 1 |
| `lab8.task4.StandardLoanTermsPolicy` | `calculate` викликає перевірку категорії | 1 |
| `lab8.task4.RareLoanTermsPolicy` | `calculate` викликає перевірку категорії | 1 |
| `lab8.task4.AcademicLoanTermsPolicy` | `calculate` викликає перевірку категорії | 1 |
| `lab8.task4.LoanTerms` | `totalPayable` працює зі складниками умов видачі | 1 |

**Висновок за LCOM4:** виміряні класи з бізнес-поведінкою мають `LCOM4 = 1`, що відповідає цільовій згуртованості. Значення 1 для legacy-класу та контролера з одним методом не доводить відсутність змішаних обов'язків: LCOM4 не розбиває один метод на окремі кроки.

## Підсумок

У варіанті 4 правила застави та сервісного збору ізольовані поліморфними політиками; доменні класи Task 2 і політики Task 4 мають `CBO ≤ 4`, а згуртованість бізнес-класів — `LCOM4 = 1`. Єдиний явний виняток — `LoanController` (`CBO = 12`): інтеграція завдань 2–4 залишила в ньому надто багато залежностей і дубльовані типи книги та категорії. Цей висновок фіксує фактичну метрику та конкретний напрямок подальшого рефакторингу, замість заявляти про недоведене досягнення порогу.
