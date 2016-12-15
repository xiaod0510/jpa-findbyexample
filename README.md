FindByExample - Using Spring Data JPA
=========================
A good example for how to do a findByExample in JPA.
The BaseExample extend the JPA Specification interface.
### e.g:
```java
    public static void main(String[] args) {
        ContactExample example =
                ContactExample
                        .where()//default is and
                        .id.eq(1l)
                        .or()
                        .birthday.lt(new Date())
                        .name.like("%", "name");
        //this example is "where id =1 or( birthday < #birthday# and name like '%#name#')"
        syntax(example);
    }
    // search by interface JpaSpecificationExecutor 
    public static void syntax(ContactExample example) {
        //init by springcontext
        ContactRepository repository = null;
        
        Contact contact = repository.findOne(example);

        List<Contact> list = repository.findAll(example);

        Page<Contact> page = repository.findAll(example, new PageRequest(0, 10));

        Page<Contact> pageAndSort = repository.findAll(example, new PageRequest(0, 10, new Sort("id")));

    }
```

Usage:
--------
### Create Entity Bean and JpaRepository(extends JpaSpecificationExecutor):
```java
    @Entity
    public class Contact {
        @Id
        @GeneratedValue
        private Long id;
        @Column
        private String name;
        @Column
        private Date birthday;
        //Getter and Setter
    }
    public interface ContactRepository
            extends
            JpaSpecificationExecutor<Contact> {
    }
```

### Create your own Example:

```java
    public class ContactExample extends BaseExample<ContactExample, Contact> {
        public final Attr<Long> id = new Attr<Long>("id");
        public final Attr<String> name = new Attr<String>("name");
        public final Attr<Date> birthday = new Attr<Date>("birthday");
        //default builder  
        public static ContactExample where() {
            ContactExample example = new ContactExample();
            example.operatorType = OperatorType.and;
            return example;
        }
    }
```

### API:

```java
    
    public static void main(String[] args) {
        ContactExample
            .where()//default is and

            .id.eq(1l)
            .id.notEq(1l)

            .id.gt(1l)
            .id.gtEq(1l)

            .id.lt(1l)
            .id.ltEq(1l)

            .id.in(1l)
            .id.in(1l,2l)
            .id.in(new Long[]{})
            .id.in(new ArrayList())

            .id.notIn(1l)

            .id.between(1l,2l)

            .id.isNull()
            .id.isNotNull()

            .and()
                //...
            .or()
                //...
            ;
    }
```
TODO list:
--------
- group by
- subquery
