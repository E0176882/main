package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.distribute.Distribute;
import seedu.address.model.group.AddGroup;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;

    /**
     * Clears existing backing model and replaces with the provided new data.
     */
    void resetData(ReadOnlyAddressBook newData);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void updatePerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    //@@author rajdeepsh

    /**
     * Adds a group to the address book.
     * The group must not already exist in the address book.
     *
     * @param group Group to add.
     */
    void createGroup(Group group);

    /**
     * Returns true if a group with the same identity fields
     * as {@code group} exists in the address book.
     *
     * @param group Group to check for.
     * @return Check result.
     */
    boolean hasGroup(Group group);

    /**
     * Adds persons to a group in the address book.
     * The persons must not already exist in the group.
     * The group must exist in the address book.
     *
     * @param addGroup Contains group and persons to add to group.
     */
    void addGroup(AddGroup addGroup);

    /**
     * Return true if a person is already in the specified group.
     *
     * @param addGroup Contains group and person to check with.
     * @return Check result.
     */
    boolean hasPersonInGroup(AddGroup addGroup);

    /**
     * Deletes {@code target} from the AddressBook.
     * {@code target} must exist in the address book.
     *
     * @param target Group to delete.
     */
    void deleteGroup(Group target);

    /**
     * Removes person from a group.
     *
     * @param group Group to remove person from.
     * @param targetPerson Person to be removed.
     */
    void deleteGroupPerson(Group group, Person targetPerson);

    /**
     * Returns an unmodifiable view of the filtered group list.
     *
     * @return Unmodifiable group list.
     */
    ObservableList<Group> getFilteredGroupList();

    /**
     * Updates the filter of the filtered group list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGroupList(Predicate<Group> predicate);

    //@@author

    /**
     * Get the default scripts location from the model
     */
    String getScriptFolderLocation();

    void executeDistributeAlgorithm(Model mode, Distribute distribute) throws CommandException;
}
