package lab7.task2;

/** Розширення для правил розрахунку податків певної категорії товарів. */
public interface ImportTaxStrategy {
    ProductCategory getCategory();

    TaxBreakdown calculate(ImportGoods goods);
}
