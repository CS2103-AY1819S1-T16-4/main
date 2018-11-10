package seedu.planner.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.planner.commons.util.JsonUtil;

public class MajorDescriptionTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    /**
     * Create dummy Major Description object to test serialization and deserialization.
     */
    private MajorDescription createDummyMajorDescription() {
        List<ModuleDescription> modules = new ArrayList<>();
        modules.add(new ModuleDescription("CS1010", DegreeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS1231", DegreeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS2030", DegreeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS2040", DegreeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS2100", DegreeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS2103T", DegreeRequirement.BREATH_AND_DEPTH,
                List.of(FocusArea.SOFTWARE_ENGINEERING)));
        modules.add(new ModuleDescription("CS2105", DegreeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS2106", DegreeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS3230", DegreeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("CP3880", DegreeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("CP3200", DegreeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("CP3202", DegreeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("IS4010", DegreeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("CP3200", DegreeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("CP3107", DegreeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("IS1103", DegreeRequirement.IT_PROFESSIONALISM));

        return new MajorDescription(Major.COMPUTER_SCIENCE, modules);
    }

    @Test
    public void testSerializationAndDeserialization() throws IOException {
        MajorDescription majorDescription = createDummyMajorDescription();
        Map<Major, MajorDescription> map = new HashMap<>();
        map.put(Major.COMPUTER_SCIENCE, majorDescription);

        String s = JsonUtil.toJsonString(map);

        Map<Major, MajorDescription> deserialized = JsonUtil.getObjectMapper().readValue(s,
                MajorDescription.MAP_TYPE_REF);
        assertEquals(deserialized, map);
    }

    @Test
    public void equals() {
        ModuleDescription cs1010 = new ModuleDescription("CS1010", DegreeRequirement.FOUNDATION);
        MajorDescription cs = new MajorDescription(Major.COMPUTER_SCIENCE, List.of(cs1010));

        // same object -> returns true
        assertTrue(cs.equals(cs));

        // null -> return false
        assertFalse(cs.equals(null));

        // different type -> returns false
        assertFalse(cs.equals(5));

        ModuleDescription fakeModuleDescription = new ModuleDescription("CS1010",
                DegreeRequirement.IT_PROFESSIONALISM);
        MajorDescription fakeMajorDescription = new MajorDescription(Major.COMPUTER_SCIENCE,
                List.of(fakeModuleDescription));

        // different module description list -> false
        assertFalse(cs.equals(fakeMajorDescription));
    }
}
