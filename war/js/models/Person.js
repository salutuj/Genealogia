var Person = (function() {

  var getId = function() {
    return id;
  }

  var setId = function(id) {
    return this.id = id;
  }

  var getAge = function() {
    return age;
  }

  var setAge = function(age) {
    return this.age = age;
  }

  var getFirstName = function() {
    return firstName;
  }

  var setFirstName = function(firstName) {
    return this.firstName = firstName;
  }

  var getMiddleName = function() {
    return middleName;
  }

  var setMiddleName = function(middleName) {
    return this.middleName = middleName;
  }

  var getMaidenName = function() {
    return maidenName;
  }

  var setMaidenName = function(maidenName) {
    return this.maidenName = maidenName;
  }

  var getLastName = function() {
    return lastName;
  }

  var setLastName = function(lastName) {
    return this.lastName = lastName;
  }

  var getBirthEvent = function() {
    return birthEvent;
  }

  var setBirthEvent = function(birthEvent) {
    return this.birthEvent = birthEvent;
  }

  var getDeathEvent = function() {
    return deathEvent;
  }

  var setDeathEvent = function(deathEvent) {
    return this.deathEvent = deathEvent;
  }

  var getEvents = function() {
    return events;
  }

  var setEvents = function(events) {
    return this.events = events;
  }

  var getFamilyAsSpouse = function() {
    return familyAsSpouse;
  }

  var setFamilyAsSpouse = function(familyAsSpouse) {
    return this.familyAsSpouse = familyAsSpouse;
  }

  var getFamilyAsChild = function() {
    return familyAsChild;
  }

  var setFamilyAsChild = function(familyAsChild) {
    return this.familyAsChild = familyAsChild;
  }

  var getRelationToParent = function() {
    return this.relationToParent;
  }

  var setRelationToParent  = function(relationToParent) {
    return this.relationToParent = father;
  }
  
  var getFather = function() {
    return father;
  }

  var setFather = function(father) {
    return this.father = father;
  }

  var getMother = function() {
    return mother;
  }

  var setMother = function(mother) {
    return this.mother = mother;
  }

  var getChildren = function() {
    return children;
  }

  var setChildren = function(children) {
    return this.children = children;
  }
  
  return {
    getId : getId,
    setId : setId,
    getAge : getAge,
    setAge : setAge,
    getFirstName : getFirstName,
    setFirstName : setFirstName,
    getMiddleName : getMiddleName,
    setMiddleName : setMiddleName,
    getMiddleName : getMiddleName,
    setMiddleName : setMiddleName,
    getLastName : getLastName,
    setLastName : setLastName,
    getBirthEvent : getBirthEvent,
    setBirthEvent : setBirthEvent,
    getDeathEvent : getDeathEvent,
    setDeathEvent : setDeathEvent,
    getEvents : getEvents,
    setEvents : setEvents,
    getFamilyAsSpouse : getFamilyAsSpouse,
    setFamilyAsSpouse : setFamilyAsSpouse,
    getFamilyAsChild : getFamilyAsChild,
    setFamilyAsChild : setFamilyAsChild,
    getRelationToParent : getRelationToParent,
    setRelationToParent : setRelationToParent,
    getFather : getFather,
    setFather : setFather,
    getMother : getMother,
    setMother : setMother,
    getChildren : getChildren,
    setChildren : setChildren
  }
})();