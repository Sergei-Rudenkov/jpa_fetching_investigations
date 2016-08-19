# JPA Eager fetch problem 

Created this project in order to investigate Fetching strategies, how do Hibernate resolv cyclic reference in a not trivial situations.  

### I used:
  - __Java 1.8__
  - __Spring boot__ 1.3.1.RELEASE
  - Latest for time of writting __Hibernate__ 5.2.4.Final
  - Postgresql 9.4

I faced problem the same as OP in this question:
 http://stackoverflow.com/questions/38874609/how-to-define-friendship-relationship-using-hibernate

Seems it bug agains hibernate, since references' dependencies are not trivial, and I would say _Badly Designed_, vialete sane DB normalization rules, I cannot say which one.   

> All JPA providers should be able to cope with eager loading of cyclic relations. Any problem should mean you raise a  bug against the JPA provider.

So I fixed the problem by adding _LAZY_. Initialization to make problem came up again switch it back to _EAGER_:

```java
public class Member implements Serializable {
    //...
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "requester")
    private Set<Friendship> friendRequests = new HashSet<Friendship>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "friend")
    private Set<Friendship> friends = new HashSet<Friendship>();
```
