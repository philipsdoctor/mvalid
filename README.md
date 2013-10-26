# mvalid

Desired use case, specify a map specification and then
validate map data against that spec, allow for simple
cases of 'are all of them valid?' as well as returning error
message maps or simple concatenated strings.

## Usage

###Sample spec

> (def form-spec
>   {:first [[not-empty] "The First must not be empty!"]
>    :second [[#(> % 4)]] "Second must be greater than 4."]
>    :thrid  [[number? #(< % 10)] "Third must be a number and less than 10."]})

###Sample data

> (def sample-data
>   {:first "first"
>    :second 2
>    :thrid 7})

###Sample Output

> (validate form-spec sample-data)
>
> {:first {:valid true :message ""}
>  :second {:valid flase :message "second must be greater than 4"}
>  :third {:valid true :message ""}}
>
> (is-valid? (validate form-spec sample-data))
>
> False


## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
