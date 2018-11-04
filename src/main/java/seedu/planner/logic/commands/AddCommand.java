package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.planner.commons.util.StringUtil.convertCollectionToString;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.AddModuleEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.util.ModuleUtil;

//@@author RomaRomama

/**
 * Add a module to the module planner
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add current/future modules to the module planner. "
            + "Parameters: "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + PREFIX_CODE + "MODULE CODE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "2 "
            + PREFIX_SEMESTER + "2 "
            + PREFIX_CODE + "CS3244 ";

    public static final String MESSAGE_SUCCESS = "Added Module(s): %1$s";

    public static final String MESSAGE_EQUIVALENT = "Following module(s) are equivalent: %1$s";
    public static final String MESSAGE_EXISTED_MODULES = "Following module(s) already exist in the planner: %1$s";
    public static final String MESSAGE_PRECLUDED_MODULES = "Following module(s) have some of their preclusions"
            + " not fulfilled in the planner: %1$s";
    public static final String MESSAGE_UNFULFILLED = "Following module(s) have their prerequisites not fulfilled"
            + " in the planner: %1$s";

    private final int semesterIndex;
    private final Set<Module> modulesToAdd;

    /**
     * Add module method
     */
    public AddCommand(Set<Module> modules, int index) {
        requireAllNonNull(modules, index);
        semesterIndex = index;
        modulesToAdd = modules;
    }

    /**
     * Retrieves all invalid modules from the list.
     *
     * @param modules List of modules
     * @param model Model used
     * @return List of invalid modules
     */
    private List<Module> retrieveInvalidModules(Set<Module> modules, Model model) {
        List<Module> invalidModules = new ArrayList<>();
        for (Module m : modules) {
            if (!model.isModuleOffered(m)) {
                invalidModules.add(m);
            }
        }

        return invalidModules;
    }

    /**
     * Retrieves all existing modules in the model from the list.
     *
     * @param modules List of modules
     * @param model Model used
     * @return List of existing modules
     */
    private List<Module> retrieveExistingModules(Set<Module> modules, Model model) {
        List<Module> existingModules = new ArrayList<>();
        for (Module m : modules) {
            if (model.hasModule(m)) {
                existingModules.add(m);
            }
        }

        return existingModules;
    }

    /**
     * Retrieves all precluded modules from the list based from the model.
     *
     * @param modules List of modules
     * @param model Model used
     * @return List of precluded modules
     */
    private Set<Module> retrievePrecludedModules(Set<Module> modules, Model model) {
        Set<Module> precludedModules = new HashSet<>();
        for (Module m : modules) {
            List<ModuleInfo> preclusions = m.getPreclusions();
            for (ModuleInfo preclusion: preclusions) {
                if (model.hasModule(new Module(preclusion.getCode()))) {
                    precludedModules.add(m);
                }
            }
        }

        return precludedModules;
    }

    /**
     * Retrieves all equivalent modules from the list.
     *
     * @param modules list of modules
     * @return all grouping of equivalent modules with more than 1 member
     */
    private List<List<Module>> retrieveEquivalentModules(Set<Module> modules) {
        return ModuleUtil.findModuleEquivalences(new ArrayList<>(modules));
    }

    /**
     * Retrieves all modules from the list which some of their prerequisites has not been fulfilled in the Model.
     *
     * @param modules List of modules
     * @param model Model used
     * @return List of all unfulfilled modules
     */
    private List<Module> retrieveUnfulfilledModules(Set<Module> modules, Model model) {
        List<Module> unfulfilledModules = new ArrayList<>();
        int i = 0;
        List<Module> upToIndex = new ArrayList<>();

        while (i < semesterIndex) {
            upToIndex.addAll(model.getTakenModules(i));
            i++;
        }

        for (Module m : modules) {
            if (!ModuleUtil.hasFulfilledAllPrerequisites(upToIndex, m)) {
                unfulfilledModules.add(m);
            }
        }

        return unfulfilledModules;
    }
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        Set<Module> copyToAdd = new HashSet<>(modulesToAdd);

        List<Module> invalidModules = retrieveInvalidModules(copyToAdd, model);
        copyToAdd.removeAll(invalidModules);

        List<Module> existedModules = retrieveExistingModules(copyToAdd, model);
        copyToAdd.removeAll(existedModules);

        Set<Module> precludedModules = retrievePrecludedModules(copyToAdd, model);
        copyToAdd.removeAll(precludedModules);

        List<List<Module>> equivalentModules = retrieveEquivalentModules(copyToAdd);
        for (List<Module> lm : equivalentModules) {
            copyToAdd.removeAll(lm);
        }

        List<Module> unfulfilledModules = retrieveUnfulfilledModules(copyToAdd, model);
        copyToAdd.removeAll(unfulfilledModules);

        String result = String.format(MESSAGE_SUCCESS, convertCollectionToString(copyToAdd));

        if (!invalidModules.isEmpty()) {
            result += "\n" + String.format(Messages.MESSAGE_INVALID_MODULES, convertCollectionToString(invalidModules));
        }

        if (!existedModules.isEmpty()) {
            result += "\n" + String.format(MESSAGE_EXISTED_MODULES, convertCollectionToString(existedModules));
        }

        if (!precludedModules.isEmpty()) {
            result += "\n" + String.format(MESSAGE_PRECLUDED_MODULES, convertCollectionToString(precludedModules));
        }

        if (!equivalentModules.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (List<Module> equivalence : equivalentModules) {
                sb.append("(");
                sb.append(convertCollectionToString(equivalence));
                sb.append(") ");
            }
            result += "\n" + String.format(MESSAGE_EQUIVALENT, sb.toString().trim());
        }

        if (!unfulfilledModules.isEmpty()) {
            result += "\n" + String.format(MESSAGE_UNFULFILLED, convertCollectionToString(unfulfilledModules));
        }

        model.addModules(copyToAdd, semesterIndex);
        model.commitModulePlanner();

        EventsCenter.getInstance().post(new AddModuleEvent(semesterIndex));
        return new CommandResult(result);

    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand command = (AddCommand) other;
        return modulesToAdd.equals(command.modulesToAdd) && semesterIndex == command.semesterIndex;
    }
}
