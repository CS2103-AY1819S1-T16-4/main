package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.areEqualIgnoreOrder;
import static seedu.planner.commons.util.CollectionUtil.convertCollectionToString;
import static seedu.planner.commons.util.CollectionUtil.formatMessage;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.AddEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
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
            + PREFIX_CODE + "MODULE CODE... "
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

    private String message;
    /**
     * Add module method
     */
    public AddCommand(Set<Module> modules, int index) {
        requireAllNonNull(modules, index);
        semesterIndex = index;
        modulesToAdd = new HashSet<>(modules);
        message = "";
    }

    /**
     * Retrieves all modules not offered from the set based on the model.
     *
     * @param model Model used
     * @return List of modules not offered
     */
    private List<Module> retrieveNotOfferedModules(Model model) {
        List<Module> notOfferedModules = new ArrayList<>();
        for (Module m : modulesToAdd) {
            if (!model.isModuleOffered(m)) {
                notOfferedModules.add(m);
            }
        }

        return notOfferedModules;
    }

    /**
     * Retrieves all modules from the set that exist in the model.
     *
     * @param model Model used
     * @return List of existing modules
     */
    private List<Module> retrieveExistingModules(Model model) {
        List<Module> existingModules = new ArrayList<>();
        for (Module m : modulesToAdd) {
            if (model.hasModule(m)) {
                existingModules.add(m);
            }
        }

        return existingModules;
    }

    /**
     * Retrieves all precluded modules from the set based from the model.
     *
     * @param model Model used
     * @return List of precluded modules
     */
    private Set<Module> retrievePrecludedModules(Model model) {
        Set<Module> precludedModules = new HashSet<>();
        for (Module m : modulesToAdd) {
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
     * Retrieves all equivalent modules from the set.
     *
     * @return all grouping of equivalent modules with more than 1 member
     */
    private List<List<Module>> retrieveEquivalentModules() {
        return ModuleUtil.findModuleEquivalences(new ArrayList<>(modulesToAdd));
    }

    /**
     * Retrieves all modules from the set which some of their prerequisites has not been fulfilled in the Model.
     *
     * @param model Model used
     * @return List of all unfulfilled modules
     */
    private List<Module> retrieveUnfulfilledModules(Model model) {
        List<Module> unfulfilledModules = new ArrayList<>();
        int i = 0;
        List<Module> upToIndex = new ArrayList<>();

        while (i < semesterIndex) {
            upToIndex.addAll(model.getTakenModulesForIndex(i));
            i++;
        }

        for (Module m : modulesToAdd) {
            if (!ModuleUtil.hasFulfilledAllPrerequisites(upToIndex, m)) {
                unfulfilledModules.add(m);
            }
        }

        return unfulfilledModules;
    }

    /**
     * Remove modules not offered from the set.
     *
     * @param model Model used
     * @throws CommandException if all modules in the set are not offered
     */
    private void removeNotOfferedModules(Model model) throws CommandException {
        List<Module> notOfferedModules = retrieveNotOfferedModules(model);
        if (!notOfferedModules.isEmpty()) {
            boolean areAllModulesOffered = notOfferedModules.size() == modulesToAdd.size();
            message += formatMessage(Messages.MESSAGE_NOT_OFFERED_MODULES, notOfferedModules) + "\n";
            if (areAllModulesOffered) {
                throw new CommandException(message.trim());
            } else {
                modulesToAdd.removeAll(notOfferedModules);
            }
        }
    }

    /**
     * Remove all existing modules from the set.
     *
     * @param model Model used
     * @throws CommandException if all modules in the set already exist in the model
     */
    private void removeExistingModules(Model model) throws CommandException {
        List<Module> existingModules = retrieveExistingModules(model);
        if (!existingModules.isEmpty()) {
            boolean areAllModulesExist = existingModules.size() == modulesToAdd.size();
            message += formatMessage(MESSAGE_EXISTED_MODULES, existingModules) + "\n";
            if (areAllModulesExist) {
                throw new CommandException(message.trim());
            } else {
                modulesToAdd.removeAll(existingModules);
            }
        }
    }

    /**
     * Remove all precluded modules.
     *
     * @param model Model used
     * @throws CommandException if all modules in the set is precluded
     */
    private void removePrecludedModules(Model model) throws CommandException {
        Set<Module> precludedModules = retrievePrecludedModules(model);
        if (!precludedModules.isEmpty()) {
            boolean areAllModulesPrecluded = precludedModules.size() == modulesToAdd.size();
            message += formatMessage(MESSAGE_PRECLUDED_MODULES, precludedModules) + "\n";
            if (areAllModulesPrecluded) {
                throw new CommandException(message.trim());
            } else {
                modulesToAdd.removeAll(precludedModules);
            }
        }
    }

    /**
     * Remove all equivalent modules.
     *
     * @throws CommandException if no single modules in the set
     */
    private void removeEquivalentModules() throws CommandException {
        List<List<Module>> equivalentModules = retrieveEquivalentModules();
        if (!equivalentModules.isEmpty()) {
            int numberOfModules = 0;
            StringBuilder sb = new StringBuilder();
            for (List<Module> equivalence : equivalentModules) {
                numberOfModules += equivalence.size();
                sb.append("(").append(convertCollectionToString(equivalence)).append(") ");
            }
            boolean areAllModuleNonSingle = numberOfModules == modulesToAdd.size();
            message += String.format(MESSAGE_EQUIVALENT, sb.toString().trim()) + "\n";
            if (areAllModuleNonSingle) {
                throw new CommandException(message.trim());
            } else {
                for (List<Module> equivalence : equivalentModules) {
                    modulesToAdd.removeAll(equivalence);
                }
            }
        }
    }

    /**
     * Remove all unfulfilled modules.
     *
     * @param model Model used
     * @throws CommandException if all modules in the set is unfulfilled
     */
    private void removeUnfulfilledModules(Model model) throws CommandException {
        List<Module> unfulfilledModules = retrieveUnfulfilledModules(model);
        if (!unfulfilledModules.isEmpty()) {
            boolean areAllModuleUnfulfilled = unfulfilledModules.size() == modulesToAdd.size();
            message += formatMessage(MESSAGE_UNFULFILLED, unfulfilledModules) + "\n";
            if (areAllModuleUnfulfilled) {
                throw new CommandException(message.trim());
            } else {
                modulesToAdd.removeAll(unfulfilledModules);
            }
        }
    }
    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        removeNotOfferedModules(model);
        removeExistingModules(model);
        removePrecludedModules(model);
        removeEquivalentModules();
        removeUnfulfilledModules(model);

        model.addModules(modulesToAdd, semesterIndex);
        model.commitModulePlanner();

        String successMessage = formatMessage(MESSAGE_SUCCESS, modulesToAdd);
        message = successMessage + "\n" + message;
        EventsCenter.getInstance().post(new AddEvent(semesterIndex));
        return new CommandResult(message.trim());
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
        return areEqualIgnoreOrder(modulesToAdd, command.modulesToAdd)
                && semesterIndex == command.semesterIndex;
    }
}
