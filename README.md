# jpa-1toN-set
Sample application to demonstrate issue with `@OneToMany` association while using set.

## Issue
Unable to delete one child entity from association while using `Set`. Everything works fine if `List` is used instead of `Set`

Please refer to `list` branch for same code with `List` and a working unit test

## Follow
Follow [this stackoverflow thread](https://stackoverflow.com/questions/62242946/jpa-onetomany-set-vs-list-not-able-to-delete-one-child-entity-from-bidirect) for updates on this issue 