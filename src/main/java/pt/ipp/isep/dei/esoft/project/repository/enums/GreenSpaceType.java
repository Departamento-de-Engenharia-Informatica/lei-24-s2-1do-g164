package pt.ipp.isep.dei.esoft.project.repository.enums;

public enum GreenSpaceType {
    GARDEN{
        @Override
        public String toString() {
            return "Garden";
        }
    },
    MEDIUM_SIZED_PARK{
        @Override
        public String toString() {
            return "Medium Sized Park";
        }
    },
    LARGE_SIZED_PARK{
        @Override
        public String toString() {
            return "Large Sized Park";
        }
    }

}
