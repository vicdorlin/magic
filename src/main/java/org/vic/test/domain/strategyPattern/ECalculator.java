package org.vic.test.domain.strategyPattern;

/**
 * 策略枚举
 *
 * @author vicdor
 * @create 2017-03-02 13:35
 */
public enum ECalculator {
    ADD("+") {
        @Override
        public int excuse(int a, int b) {
            return a + b;
        }
    },
    SUB("-") {
        @Override
        public int excuse(int a, int b) {
            return a - b;
        }
    };

    private String value;

    private ECalculator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract int excuse(int a, int b);
}
