package seedu.planner.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.commons.util.JsonUtil;

public class MajorDescriptionTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    private MajorDescription createDummyMajorDescription() {
        List<ModuleDescription> modules = new ArrayList<>();
        modules.add(new ModuleDescription("CS1010", ProgrammeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS1231", ProgrammeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS2030", ProgrammeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS2040", ProgrammeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS2100", ProgrammeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS2103T", ProgrammeRequirement.BREATH_AND_DEPTH,
                List.of(FocusArea.SOFTWARE_ENGINEERING)));
        modules.add(new ModuleDescription("CS2105", ProgrammeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS2106", ProgrammeRequirement.FOUNDATION));
        modules.add(new ModuleDescription("CS3230", ProgrammeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("CP3880", ProgrammeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("CP3200", ProgrammeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("CP3202", ProgrammeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("IS4010", ProgrammeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("CP3200", ProgrammeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));
        modules.add(new ModuleDescription("CP3107", ProgrammeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT));

        modules.add(new ModuleDescription("IS1103", ProgrammeRequirement.IT_PROFESSIONALISM));

        return new MajorDescription(Major.COMPUTER_SCIENCE, modules);
    }

    @Test
    public void testSerializationAndDeserialization() throws IOException, DataConversionException {
        MajorDescription majorDescription = createDummyMajorDescription();

        Path tempFilePath = testFolder.getRoot().toPath().resolve("TempMajorDescription.json");
        JsonUtil.saveJsonFile(new MajorDescription[] {majorDescription}, tempFilePath);

        MajorDescription[] deserializedMajorDescription = JsonUtil.readJsonFile(tempFilePath,
                MajorDescription[].class).get();
        assertEquals(deserializedMajorDescription.length, 1);
        assertEquals(majorDescription, deserializedMajorDescription[0]);
    }
}
