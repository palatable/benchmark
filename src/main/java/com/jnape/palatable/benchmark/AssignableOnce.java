package com.jnape.palatable.benchmark;

class AssignableOnce<Value> {

    private Value value;
    private boolean assigned = false;

    public AssignableOnce() {
    }

    public AssignableOnce(Value value) {
        this.value = value;
        assigned = true;
    }

    public Value get() {
        ensureAlreadyAssigned();
        return value;
    }

    public void set(Value value) {
        ensureNotAlreadyAssigned();
        this.value = value;
        assigned = true;
    }

    private void ensureAlreadyAssigned() {
        if (!assigned)
            throw new IllegalStateException("Value hasn't yet been assigned.");
    }

    private void ensureNotAlreadyAssigned() {
        if (assigned)
            throw new IllegalStateException("Values are final once assigned.");
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AssignableOnce) {
            AssignableOnce that = (AssignableOnce) other;

            boolean bothUnassigned = !this.assigned && !that.assigned;
            boolean bothAssigned = this.assigned && that.assigned;
            boolean bothEqual = get().equals(that.get());

            return bothUnassigned || bothAssigned && bothEqual;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = assigned ? value.hashCode() : 0;
        return 31 * result + 1;
    }
}
