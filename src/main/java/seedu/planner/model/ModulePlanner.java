package seedu.planner.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import seedu.planner.commons.core.LogsCenter;
import seedu.planner.model.course.DegreeRequirement;
import seedu.planner.model.course.FocusArea;
import seedu.planner.model.course.Major;
import seedu.planner.model.course.MajorDescription;
import seedu.planner.model.course.ModuleDescription;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.semester.Semester;
import seedu.planner.model.user.UserProfile;
import seedu.planner.model.util.IndexUtil;
import seedu.planner.model.util.ModuleUtil;

//@@author Hilda-Ang

/**
 * Wraps all data at the module planner level.
 */
public class ModulePlanner implements ReadOnlyModulePlanner {
    public static final int MAX_NUMBER_SEMESTERS = 8;
    public static final int MAX_SEMESTERS_PER_YEAR = 2;

    private static final int ALL_YEARS = -1;

    private static Logger logger = LogsCenter.getLogger(ModulePlanner.class);

    private final List<Semester> semesters;
    private UserProfile userProfile;

    private final ObservableList<Module> availableModules = FXCollections.observableArrayList();
    private final ObservableList<Module> takenModules = FXCollections.observableArrayList();


    private ObservableMap<DegreeRequirement, int[]> statusMap = FXCollections.observableHashMap();
    private int availableIndex;
    private int takenIndex;

    /**
     * Constructs a {@code ModulePlanner} and initializes an array of 8 {@code Semester}
     * to store details of each {@code Semester}.
     */
    public ModulePlanner() {
        logger.info("initialising a new ModulePlanner");
        semesters = new ArrayList<>(MAX_NUMBER_SEMESTERS);
        userProfile = new UserProfile();

        for (int i = 1; i <= MAX_NUMBER_SEMESTERS / MAX_SEMESTERS_PER_YEAR; i++) {
            for (int j = 1; j <= MAX_SEMESTERS_PER_YEAR; j++) {
                semesters.add(new Semester(i, j));
            }
        }

        availableIndex = 0;
        takenIndex = ALL_YEARS;
    }

    /**
     * Creates a {@code ModulePlanner} using the {@code Module}s in the {@code toBeCopied}
     */
    public ModulePlanner(ReadOnlyModulePlanner toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Add one or more module(s) to set of modules taken for the specified semester.
     *
     * @param modules A set of valid modules to be added
     * @param index   A valid semester
     */
    public void addModules(Set<Module> modules, int index) {
        semesters.get(index).addModules(modules);
        updateAvailableModules();
        updateTakenModules();
        getStatus();
    }

    //@@author GabrielYik

    /**
     * Delete one or more module(s) from list of modules taken for the specified semester.
     *
     * @param modules A list of valid modules to be deleted
     */
    public void deleteModules(Set<Module> modules) {
        // Iterate once to delete the specified modules
        for (Semester semester : semesters) {
            semester.deleteModules(modules);
        }

        /*
         * Delete invalidated modules repeatedly until there are no more changes
         * to the total number of modules in the module planner
         */
        Set<Module> deletedModules = new HashSet<>(modules);
        int previousModuleCount = countModules();
        while (true) {
            for (int index = 0; index < MAX_NUMBER_SEMESTERS; index++) {
                Semester semester = semesters.get(index);
                List<Module> invalidatedModules = new ArrayList<>();
                invalidateModules(semester, deletedModules, invalidatedModules);
                deleteInvalidatedModules(semester, deletedModules, invalidatedModules);
            }

            int currentModuleCount = countModules();
            if (currentModuleCount == previousModuleCount) {
                break;
            }
            if (currentModuleCount < previousModuleCount) {
                previousModuleCount = currentModuleCount;
            }
        }

        updateAvailableModules();
        updateTakenModules();
        getStatus();
    }

    /**
     * Counts the number of modules in the module planner.
     *
     * @return The total number of modules
     */
    private int countModules() {
        int count = 0;
        for (Semester semester : semesters) {
            count += semester.getModules().size();
        }
        return count;
    }

    /**
     * Checks if all the modules in {@code semester} have their prerequisites
     * fulfilled. If any of the modules do not, they will be added to
     * {@code invalidatedModules}.
     *
     * @param semester           The semester which modules are to be checked
     * @param deletedModules     The modules to be checked against
     * @param invalidatedModules The group of modules any of the modules in
     *                           {@code semester} will be added to if it does not fulfill all of
     *                           it's prerequisites
     */
    private void invalidateModules(Semester semester, Set<Module> deletedModules, List<Module> invalidatedModules) {
        for (Module module : semester.getModules()) {
            List<ModuleInfo> prerequisites = module.getPrerequisites();
            if (!prerequisites.isEmpty()) {
                boolean hasHadPrerequisiteDeleted = prerequisites.stream().anyMatch(x ->
                        deletedModules.stream().anyMatch(y -> x.getCode().equals(y.getCode())));
                if (hasHadPrerequisiteDeleted) {
                    invalidatedModules.add(module);
                }
            }
        }
    }

    /**
     * Deletes the {@code invalidatedModules} from the modules {@code semester} has.
     *
     * @param semester           The semester which the {@code invalidatedModules} are to be
     *                           deleted from
     * @param invalidatedModules The invalidated modules
     */
    private void deleteInvalidatedModules(Semester semester, Set<Module> deletedModules,
                                          List<Module> invalidatedModules) {
        if (!invalidatedModules.isEmpty()) {
            Set<Module> modulesToDelete = new HashSet<>(invalidatedModules);
            semester.deleteModules(modulesToDelete);
            deletedModules.addAll(modulesToDelete);
        }
    }

    /**
     * Checks if the {@code Module} exists in the module planner.
     *
     * @param module The module to check
     * @return True if the module exists, false if not
     */
    public boolean hasModule(Module module) {
        for (Semester semester : semesters) {
            if (semester.containsModule(module)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile u) {
        userProfile = u;
        getStatus();
    }

    @Override
    public List<Semester> getSemesters() {
        List<Semester> semestersCopy = new ArrayList<>();
        for (Semester semester : semesters) {
            Semester semesterCopy = new Semester(semester);
            semestersCopy.add(semesterCopy);
        }
        return semestersCopy;
    }

    /**
     * Resets the existing data of this {@code ModulePlanner} with {@code newData}.
     */
    public void resetData(ReadOnlyModulePlanner newData) {
        requireNonNull(newData);
        updateAvailableModules();
        updateTakenModules();
        setModulesInSemesters(newData.getSemesters());
    }


    public void setModulesInSemesters(List<Semester> semesters) {
        for (int i = 0; i < MAX_NUMBER_SEMESTERS; i++) {
            this.semesters.get(i).setTakenModules(semesters.get(i));
        }
    }

    @Override
    public ObservableList<Module> getTakenModulesForIndex(int index) {
        return FXCollections.unmodifiableObservableList(semesters.get(index).getModules());
    }

    /**
     * Updates {@code modulesTaken} to contain all modules user has taken for all years.
     */
    public void listTakenModulesAll() {
        List<Module> modules = new ArrayList<>();

        for (Semester s : semesters) {
            modules.addAll(s.getModules());
        }
        takenIndex = ALL_YEARS;
        setTakenModules(modules);
    }

    /**
     * Updates {@code modulesTaken} to contain all modules user has taken in a specified year.
     *
     * @param year An integer between 1 to 4 inclusive.
     */
    public void listTakenModulesForYear(int year) {
        int[] indices = IndexUtil.getIndicesFromYear(year);
        List<Module> modules = new ArrayList<>();

        for (int i = 0; i < indices.length; i++) {
            modules.addAll(semesters.get(indices[i]).getModules());
        }

        takenIndex = year;
        setTakenModules(modules);
    }

    @Override
    public ObservableList<Module> getTakenModules() {
        return takenModules;
    }

    /**
     * Updates {@code takenModules} according to the latest displayed list upon add or delete command.
     */
    private void updateTakenModules() {
        logger.info("updating list of taken modules");
        if (takenIndex == ALL_YEARS) {
            listTakenModulesAll();
        } else {
            listTakenModulesForYear(takenIndex);
        }
    }

    /**
     * Replaces the contents of the internal {@code takenModules} list with the given list of {@code Module}s.
     * List of modules supplied must not contain duplicates.
     */
    private void setTakenModules(List<Module> modules) {
        takenModules.setAll(modules);
    }

    /**
     * Updates the list of modules available based on given index and stores the index for add and delete commands.
     *
     * @param index An integer from 0 to 7 inclusive indicating the year and semester to suggest.
     */
    public void suggestModules(int index) {
        availableIndex = index;
        updateAvailableModules();
    }

    @Override
    public ObservableList<Module> getAvailableModules() {
        updateAvailableModules();
        return availableModules;
    }

    /**
     * Updates internal list of available {@code Module}s based on stored index.
     */
    private void updateAvailableModules() {
        logger.info("updating list of available modules");
        setAvailableModules(generateAvailableModules(availableIndex));
    }

    /**
     * Replaces the contents of the internal {@code availableModules} list with the given list of {@code Module}s.
     * List of modules supplied must not contain duplicates.
     */
    private void setAvailableModules(List<Module> modules) {
        availableModules.setAll(modules);
    }

    /**
     * Get a list of all {@code Module}s user can take based on the {@code Module}s user has taken.
     *
     * @param index An integer from 0 to 7 inclusive to inidcate the current year and semester to suggest.
     * @return A list of {@code Module}s the user is available to take.
     */
    private List<Module> generateAvailableModules(int index) {
        List<Module> modulesAvailable = new ArrayList<>();
        List<Module> modulesTaken = getAllModulesTaken();
        List<Module> modulesTakenBeforeIndex = getAllModulesTakenBeforeIndex(index);
        List<Module> allModules = getAllModulesFromStorage();

        for (Module m : allModules) {
            if (ModuleUtil.isModuleAvailable(modulesTaken, modulesTakenBeforeIndex, m)) {
                modulesAvailable.add(m);
            }
        }

        sortAvailableModules(modulesAvailable, userProfile);

        return modulesAvailable;
    }

    // @@author rongjiecomputer

    /**
     * Sort {@code modulesAvailable} based on the information in {@code userProfile}.
     */
    private void sortAvailableModules(List<Module> modulesAvailable, UserProfile userProfile) {
        Major major = userProfile.getMajor();
        MajorDescription majorDescription = MajorDescription.getFromMajor(major).orElse(new MajorDescription());
        // Note: Collections.sort uses stable sort when sorting objects, which we are exploiting here so that
        // we can chain our sorting and still making sure that the order created by each comparator is preserved.
        //
        // The order of comparators you applied to the list matters!

        // Step 1. If we have the information for this major, we stop immediately.

        logger.info(String.format("Requirements for user's major (%s) found. Prioritize modules start with %s.",
                major, majorDescription.getPrefixes()));

        List<String> prefixes = new ArrayList<>(majorDescription.getPrefixes());
        // Add GE module prefixes for consideration as well.
        prefixes.addAll(List.of("GER", "GEQ", "GES", "GET", "GEH"));

        // Step 2. Move modules that matches prefixes to the front of available module list.
        Comparator<Module> moveFacultyModuleToFront = (Module lhs, Module rhs) -> {
            return Integer.compare(
                    ModuleUtil.rankModuleCodePrefixes(lhs.getCode(), prefixes),
                    ModuleUtil.rankModuleCodePrefixes(rhs.getCode(), prefixes));
        };
        Collections.sort(modulesAvailable, moveFacultyModuleToFront);

        // Step 3. Move more prioritized modules to the front of available module list.
        Comparator<Module> moveImportantModuleToFront = (Module lhs, Module rhs) -> {
            return Integer.compare(
                    ModuleUtil.rankModuleCodeFromPriorityList(lhs.getCode(), majorDescription.getModules()),
                    ModuleUtil.rankModuleCodeFromPriorityList(rhs.getCode(), majorDescription.getModules())
            );
        };
        Collections.sort(modulesAvailable, moveImportantModuleToFront);
    }

    // @@author Hilda-Ang

    /**
     * Combines the list of {@code Module}s taken from every {@code Semester}.
     *
     * @return A list of all {@code Module}s the user has taken.
     */
    private List<Module> getAllModulesTaken() {
        List<Module> modulesTaken = new ArrayList<>();
        for (Semester s : semesters) {
            modulesTaken.addAll(s.getModules());
        }
        return modulesTaken;
    }

    /**
     * Combines the list of {@code Module}s taken for every {@code Semester} before current index.
     *
     * @param index The current index user is at.
     * @return A list of all {@code Module}s the user has taken until the specified index.
     */
    private List<Module> getAllModulesTakenBeforeIndex(int index) {
        List<Module> modulesTaken = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            modulesTaken.addAll(semesters.get(i).getModules());
        }
        return modulesTaken;
    }

    /**
     * Get a list of all {@code Module}s data retrieved from external party
     * and stored internally in {@code ModulePlanner}.
     *
     * @return A list of all {@code Module}s in the storage.
     */
    private List<Module> getAllModulesFromStorage() {
        logger.info("retrieving all modules data from storage");
        ModuleInfo[] allModuleInfo = ModuleInfo.getModuleInfoList();
        List<Module> allModules = new ArrayList<>();

        for (ModuleInfo mi : allModuleInfo) {
            Module m = new Module(mi.getCode());
            allModules.add(m);
        }
        return allModules;
    }

    // @@author

    private Optional<ModuleDescription> getModuleDescription(String code) {
        return MajorDescription.getModuleCode(userProfile.getMajor(), code);
    }

    /**
     * Count the number of modules fulfilling degree requirement which is not
     * a University Level Requirement or Breadth and Depth.
     *
     * @param degreeRequirement the degree requirement
     * @return the number of modules fulfilling that degree requirement
     */
    private int countProgrammeRequirement(DegreeRequirement degreeRequirement) {
        int count = 0;
        for (Module m : getAllModulesTaken()) {
            Optional<ModuleDescription> moduleDescription = getModuleDescription(m.getCode());
            if (moduleDescription.isPresent()
                    && moduleDescription.get().getRequirement().equals(degreeRequirement)) {
                count += m.getCreditCount();
            }
        }
        return count;
    }
    /**
     * Count the number of general education modules and insert it into the credit mapping.
     */
    private void mapGeneralEducation() {
        int count = 0;
        for (Module m : getAllModulesTaken()) {
            if (m.toString().startsWith("GER") || m.toString().startsWith("GEQ")
                || m.toString().startsWith("GET") || m.toString().startsWith("GEH")
                || m.toString().startsWith("GES")) {
                count += m.getCreditCount();
            }
        }
        statusMap.put(DegreeRequirement.UNIVERSITY_LEVEL_REQUIREMENTS, new int[] {count});
    }

    /**
     * Insert the number of foundation modules to the credit mapping.
     */
    private void mapFoundation() {
        int numOfFoundation = countProgrammeRequirement(DegreeRequirement.FOUNDATION);
        statusMap.put(DegreeRequirement.FOUNDATION, new int[] {numOfFoundation});
    }

    /**
     * Insert the number of mathematics modules to the credit mapping.
     */
    private void mapMathematics() {
        int numOfMathematics = countProgrammeRequirement(DegreeRequirement.MATHEMATICS);
        statusMap.put(DegreeRequirement.MATHEMATICS, new int[] {numOfMathematics});
    }

    /**
     * Insert the number of science modules to the credit mapping.
     */
    private void mapScience() {
        int numOfScience = countProgrammeRequirement(DegreeRequirement.SCIENCE);
        statusMap.put(DegreeRequirement.SCIENCE, new int[] {numOfScience});
    }

    /**
     * Insert the number of IT professionalism modules to the credit mapping..
     */
    private void mapItProfessionalism() {
        int numOfItProfessionalism = countProgrammeRequirement(
                                        DegreeRequirement.IT_PROFESSIONALISM);
        statusMap.put(DegreeRequirement.IT_PROFESSIONALISM, new int[] {numOfItProfessionalism});
    }

    /**
     * Insert the number of industrial experience requirement modules to the credit mapping.
     */
    private void mapIndustrialExperienceRequirement() {
        int numOfIndExpReq = countProgrammeRequirement(DegreeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT);
        statusMap.put(DegreeRequirement.INDUSTRIAL_EXPERIENCE_REQUIREMENT, new int[] {numOfIndExpReq});
    }

    /**
     * Count the number of Team Project Modules and insert to the credit mapping.
     */
    private void mapTeamProject() {
        int numOfTeamProject = 0;
        for (Module m : getAllModulesTaken()) {
            Optional<ModuleDescription> moduleDescription = getModuleDescription(m.getCode());
            if (moduleDescription.isPresent()
                    && moduleDescription.get().getRequirement().equals(DegreeRequirement.BREATH_AND_DEPTH)
                    && moduleDescription.get().getFocusAreas().isEmpty()) {
                numOfTeamProject += m.getCreditCount();
            }
        }
        statusMap.put(DegreeRequirement.TEAM_PROJECT, new int[] {numOfTeamProject});
    }

    /**
     * Count the number of the user's focus area requirement and insert to the credit mapping.
     */
    private void mapFocusAreasRequirement() {
        List<FocusArea> focusAreas = new ArrayList<>(getUserProfile().getFocusAreas());
        if (!focusAreas.isEmpty()) {
            int[] numOfFocusAreasRequirement = new int[focusAreas.size()];
            for (Module m : getAllModulesTaken()) {
                Optional<ModuleDescription> moduleDescription = getModuleDescription(m.getCode());
                for (int i = 0; i < focusAreas.size(); i++) {
                    if (moduleDescription.isPresent()
                            && moduleDescription.get().getRequirement().equals(DegreeRequirement.BREATH_AND_DEPTH)
                            && !moduleDescription.get().getFocusAreas().isEmpty()
                            && moduleDescription.get().getFocusAreas().get(0).equals(focusAreas.get(i))) {
                        numOfFocusAreasRequirement[i] += m.getCreditCount();
                    }
                }
            }
            statusMap.put(DegreeRequirement.FOCUS_AREA_REQUIREMENTS, numOfFocusAreasRequirement);
        }
    }

    /**
     * Maps each requirements to the number of modules fulfilling it in the planner
     *
     * @return the mapping
     */
    public ObservableMap<DegreeRequirement, int[]> getStatus() {
        mapGeneralEducation();
        mapFoundation();
        mapMathematics();
        mapScience();
        mapItProfessionalism();
        mapIndustrialExperienceRequirement();
        mapTeamProject();
        mapFocusAreasRequirement();

        return statusMap;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModulePlanner // instanceof handles nulls
                && semesters.equals(((ModulePlanner) other).semesters));
    }

    @Override
    public int hashCode() {
        return semesters.hashCode();
    }
}
